package com.demo.service.impl;

import com.demo.model.*;
import com.demo.model.constants.OrderStatus;
import com.demo.repository.*;
import com.demo.service.BillService;
import com.demo.service.utils.MappingHelper;
import com.demo.web.dto.*;
import com.demo.web.dto.request.BillCriteria;
import com.demo.web.dto.request.BillReq;
import com.demo.web.dto.request.BillStatusUpdateReq;
import com.demo.web.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class BillServiceImpl implements BillService {
    private final ProductCartRepository productCartRepository;
    private final BillRepository billRepository;
    private final ProductBillRepository productBillRepository;
    private final CartRepository cartRepository;
    private final AccountRepository accountRepository;
    private final AddressRepository addressRepository;
    private final ShippingServiceRepository shippingServiceRepository;
    private final MappingHelper mappingHelper;

    @Override
    public void createBillByCurrentAccount(BillReq billReq) {
        Bill bill = mappingHelper.map(billReq, Bill.class);
        bill.setStatus(OrderStatus.PENDING);
        bill.setPaymentTime(new Date());

        var account = getCurrentAccount();
        bill.setAccount(account);

        var addressResource = addressRepository.findById(billReq.getAddressId())
                .orElseThrow(() -> new EntityNotFoundException(
                        Address.class.getName(), billReq.getAddressId().toString()));

        Address address = new Address();
        address.setContent(addressResource.getContent());
        address.setDistrict(addressResource.getDistrict());
        address.setProvince(addressResource.getProvince());
        address.setWard(addressResource.getWard());
        address.setFullName(addressResource.getFullName());
        address.setPhoneNumber(addressResource.getPhoneNumber());

        addressRepository.save(address);
        bill.setAddress(address);

        var shippingService = shippingServiceRepository.findById(billReq.getShippingServiceId())
                .orElseThrow(() -> new EntityNotFoundException(
                        ShippingService.class.getName(), billReq.getShippingServiceId().toString()));
        bill.setShippingService(shippingService);

        var cart = getCartCurrentUser();
        var productCartChecked = productCartRepository.findByCart_IdAndChecked(cart.getId(), true);
        List<ProductBill> productBills = productCartChecked
                .stream().map(e -> {
                    ProductBill productBill = new ProductBill();
                    productBill.setBill(bill);
                    productBill.setProductDetail(e.getProductDetail());
                    productBill.setQuantity(e.getQuantity());
                    return productBill;
                }).collect(Collectors.toList());

        billRepository.save(bill);
        productBillRepository.saveAll(productBills);
        productCartRepository.deleteAll(productCartChecked);
    }

    @Override
    public List<BillDto> getBillsOfCurrentAccount() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        var res = billRepository.findByAccount_Username(username)
                .stream().map(this::mapToBillDto).collect(Collectors.toList());
        res.sort((i1, i2) -> i2.getPaymentTime().compareTo(i1.getPaymentTime()));
        return res;
    }

    @Override
    public BillDto getBillById(Integer billId) {
        var bill = billRepository.findById(billId)
                .orElseThrow(() -> new EntityNotFoundException(Bill.class.getName(), billId.toString()));
        return mapToBillDto(bill);
    }

    @Override
    public List<BillDto> getCustomBills(BillCriteria billCriteria) {
        return billRepository.findAll(billCriteria.toSpecification()).stream()
                .map(this::mapToBillDto).collect(Collectors.toList());
    }

    @Override
    public void updateBillStatus(BillStatusUpdateReq billStatusUpdateReq) {
        if (OrderStatus.LIST_STATUS.contains(billStatusUpdateReq.getStatus())) {
            var bill = billRepository.findById(billStatusUpdateReq.getBillId())
                    .orElseThrow(() -> new EntityNotFoundException(Bill.class.getName(),
                            billStatusUpdateReq.getBillId().toString()));
            bill.setStatus(billStatusUpdateReq.getStatus());
            billRepository.save(bill);
        }
    }

    private BillDto mapToBillDto(Bill bill) {
        var billDto = mappingHelper.map(bill, BillDto.class);

        billDto.setAddress(mappingHelper.map(bill.getAddress(), AddressDto.class));
        billDto.setProductBills(bill.getProductBills()
                .stream().map(i -> {
                    var productBillDto = mappingHelper.map(i, ProductBillDto.class);
                    var productDetailDto = mappingHelper.map(i.getProductDetail(), ProductDetailDto.class);
                    productDetailDto.setProductDto(mappingHelper.map(i.getProductDetail().getProduct(), ProductDto.class));
                    productBillDto.setProductDetail(productDetailDto);
                    return productBillDto;
                }).collect(Collectors.toList()));

        var profileDto = mappingHelper.map(bill.getAccount().getProfile(), ProfileDto.class);
        profileDto.setAccountDto(mappingHelper.map(bill.getAccount(), AccountDto.class));
        billDto.setProfileDto(profileDto);

        return billDto;
    }

    private Account getCurrentAccount() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return accountRepository.findOneWithAuthoritiesByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException(Account.class.getName(), username));
    }

    private Cart getCartCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return cartRepository.findByAccount_Username(username)
                .orElseGet(() -> cartRepository.save(Cart.builder()
                        .createdDate(Instant.now())
                        .account(accountRepository.findOneWithAuthoritiesByUsername(username)
                                .orElseThrow(() -> new EntityNotFoundException(Account.class.getName(), username)))
                        .build()));
    }
}
