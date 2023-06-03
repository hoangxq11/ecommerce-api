package com.demo.service.impl;

import com.demo.model.Account;
import com.demo.model.Bill;
import com.demo.model.Discount;
import com.demo.model.Product;
import com.demo.model.constants.OrderStatus;
import com.demo.repository.BillRepository;
import com.demo.repository.ProductDetailRepository;
import com.demo.repository.ProductRepository;
import com.demo.service.StatisticalService;
import com.demo.service.utils.MappingHelper;
import com.demo.web.dto.ProductDto;
import com.demo.web.dto.request.StatisticalCriteria;
import com.demo.web.dto.response.CustomerStatisticalRes;
import com.demo.web.dto.response.GroupByStatusBillRes;
import com.demo.web.dto.response.KpiRes;
import com.demo.web.dto.response.ProductStatisticalRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class StatisticalServiceImpl implements StatisticalService {
    private final BillRepository billRepository;
    private final ProductRepository productRepository;
    private final ProductDetailRepository productDetailRepository;
    private final MappingHelper mappingHelper;

    @Override
    public List<List<Integer>> salesByYear(String year) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> sales = new ArrayList<>(Collections.nCopies(12, 0));
        List<Integer> imports = new ArrayList<>(Collections.nCopies(12, 0));

        billRepository.findByPaymentTimeByYear(year).forEach(e -> {
            String paymentTime = e.getPaymentTime().toString();
            String[] word = paymentTime.split("-");
            if (word[0].equals(year)) {
                int month = Integer.parseInt(word[1]);
                sales.set(month - 1, sales.get(month - 1) + 1);
            }
        });
        res.add(sales);

        productRepository.findByCreatedAtByYear(year).forEach(e -> {
            String createdAt = e.getCreatedAt().toString();
            String[] word = createdAt.split("-");
            if (word[0].equals(year)) {
                var productDetails = productDetailRepository.findByProduct_Id(e.getId());
                int month = Integer.parseInt(word[1]);
                imports.set(month - 1, imports.get(month - 1) + productDetails.size());
            }
        });
        res.add(imports);
        return res;
    }

    @Override
    public Map<String, Integer> bestCustomer(String year) {

        Map<Account, List<Bill>> accountGrouped = billRepository.findByPaymentTimeByYear(year)
                .stream().collect(Collectors.groupingBy(Bill::getAccount));

        List<Map.Entry<Account, List<Bill>>> list = new ArrayList<>(accountGrouped.entrySet());
        list.sort(Map.Entry.comparingByValue((b1, b2) -> {
            int count1, count2;
            count1 = b1.stream().mapToInt(e -> e.getProductBills().size()).sum();
            count2 = b2.stream().mapToInt(e -> e.getProductBills().size()).sum();
            return Integer.compare(count2, count1);
        }));

        Map<String, Integer> res = new LinkedHashMap<>();
        for (Map.Entry<Account, List<Bill>> entry : list) {
            if (res.size() > 5) break;
            int count = entry.getValue().stream().mapToInt(e -> e.getProductBills().size()).sum();
            res.put(entry.getKey().getProfile().getFullName() + "-" + entry.getKey().getProfile().getPhoneNumber(), count);
        }

        return res;
    }

    @Override
    public Map<ProductDto, Integer> bestProduct(String year) {
        Map<Product, Integer> resMark = new LinkedHashMap<>();

        billRepository.findByPaymentTimeByYear(year)
                .forEach(e -> {
                    var productBills = e.getProductBills();
                    productBills.forEach(i -> {
                        int quantity = 0;
                        if (resMark.get(i.getProductDetail().getProduct()) != null)
                            quantity = resMark.get(i.getProductDetail().getProduct());
                        resMark.put(i.getProductDetail().getProduct(), i.getQuantity() + quantity);
                    });
                });


        List<Map.Entry<Product, Integer>> list = new ArrayList<>(resMark.entrySet());
        list.sort(Map.Entry.comparingByValue((b1, b2) -> Integer.compare(b2, b1)));

        Map<ProductDto, Integer> res = new LinkedHashMap<>();
        for (Map.Entry<Product, Integer> entry : list) {
            if (res.size() > 8) break;
            res.put(mapToProductDto(entry.getKey()), entry.getValue());
        }

        return res;
    }

    @Override
    public GroupByStatusBillRes billStatus() {
        Map<String, List<Bill>> statusGrouped = billRepository.findAll()
                .stream().collect(Collectors.groupingBy(Bill::getStatus));
        for (String item : OrderStatus.LIST_STATUS)
            statusGrouped.computeIfAbsent(item, k -> new ArrayList<>());
        return GroupByStatusBillRes.builder()
                .done(statusGrouped.get(OrderStatus.DONE).size())
                .pending(statusGrouped.get(OrderStatus.PENDING).size())
                .shipping(statusGrouped.get(OrderStatus.SHIPPING).size())
                .canceled(statusGrouped.get(OrderStatus.CANCELED).size())
                .build();
    }

    @Override
    public Map<ProductStatisticalRes, Integer> bestSalesProduct(StatisticalCriteria statisticalCriteria) {
        Map<Product, Integer> resMark = new LinkedHashMap<>();

        billRepository.findAll(statisticalCriteria.toSpecification())
                .forEach(e -> {
                    var productBills = e.getProductBills();
                    productBills.forEach(i -> {
                        int quantity = 0;
                        if (resMark.get(i.getProductDetail().getProduct()) != null)
                            quantity = resMark.get(i.getProductDetail().getProduct());
                        resMark.put(i.getProductDetail().getProduct(), i.getQuantity() + quantity);
                    });
                });


        List<Map.Entry<Product, Integer>> list = new ArrayList<>(resMark.entrySet());
        list.sort(Map.Entry.comparingByValue((b1, b2) -> Integer.compare(b2, b1)));

        Map<ProductStatisticalRes, Integer> res = new LinkedHashMap<>();
        for (Map.Entry<Product, Integer> entry : list) {
            if (res.size() > 10) break;
            res.put(mapToProductStatistical(entry.getKey()), entry.getValue());
        }

        return res;
    }

    @Override
    public Map<CustomerStatisticalRes, Integer> bestCustomerByStatus(StatisticalCriteria statisticalCriteria) {
        Map<Account, List<Bill>> accountGrouped = billRepository.findAll(statisticalCriteria.toSpecification())
                .stream().collect(Collectors.groupingBy(Bill::getAccount));

        List<Map.Entry<Account, List<Bill>>> list = new ArrayList<>(accountGrouped.entrySet());
        list.sort(Map.Entry.comparingByValue((b1, b2) -> {
            int count1, count2;
            count1 = b1.stream().mapToInt(e -> e.getProductBills().size()).sum();
            count2 = b2.stream().mapToInt(e -> e.getProductBills().size()).sum();
            return Integer.compare(count2, count1);
        }));

        Map<CustomerStatisticalRes, Integer> res = new LinkedHashMap<>();
        for (Map.Entry<Account, List<Bill>> entry : list) {
            if (res.size() > 10) break;
            int count = entry.getValue().stream().mapToInt(e -> e.getProductBills().size()).sum();
            res.put(mapToCustomerStatisticalRes(entry.getKey()), count);
        }

        return res;
    }

    @Override
    public KpiRes kpiProcess() {
        StatisticalCriteria statisticalCriteria = new StatisticalCriteria();
        statisticalCriteria.setStatus(OrderStatus.DONE);
        Map<Date, List<Bill>> paymentTimeGrouped = billRepository.findAll(statisticalCriteria.toSpecification())
                .stream().collect(Collectors.groupingBy(Bill::getPaymentTime));

        List<Map.Entry<Date, List<Bill>>> list = new ArrayList<>(paymentTimeGrouped.entrySet());
        list.sort(Map.Entry.comparingByKey(Comparator.reverseOrder()));

        KpiRes res = new KpiRes();
        int index = 0;

        for (int i = index; i < index + 3 && i < list.size(); i++) {
            list.get(i).getValue().forEach(e -> {
                int count = 0;
                for (var item : e.getProductBills())
                    count += item.getQuantity();
                res.setRevenueFirstSpin(res.getRevenueFirstSpin() + count);
            });
        }
        index += 3;

        for (int i = index; i < index + 3 && i < list.size(); i++) {
            list.get(i).getValue().forEach(e -> {
                int count = 0;
                for (var item : e.getProductBills())
                    count += item.getQuantity();
                res.setRevenueSecondSpin(res.getRevenueSecondSpin() + count);
            });
        }
        index += 3;

        for (int i = index; i < index + 3 && i < list.size(); i++) {
            list.get(i).getValue().forEach(e -> {
                int count = 0;
                for (var item : e.getProductBills())
                    count += item.getQuantity();
                res.setRevenueThirdSpin(res.getRevenueThirdSpin() + count);
            });
        }

        return res;
    }

    private CustomerStatisticalRes mapToCustomerStatisticalRes(Account account) {
        return CustomerStatisticalRes.builder()
                .accountId(account.getId())
                .username(account.getUsername())
                .phoneNumber(account.getProfile().getPhoneNumber())
                .fullName(account.getProfile().getFullName())
                .build();
    }

    private ProductStatisticalRes mapToProductStatistical(Product e) {
        ProductStatisticalRes res = mappingHelper.map(e, ProductStatisticalRes.class);
        var productDetail = productDetailRepository.findFirstByProduct_Id(e.getId());
        float price = 0F;
        if (productDetail.isPresent()) {
            price = productDetail.get().getPrice();
        }
        res.setPrice(price);
        return res;
    }

    private ProductDto mapToProductDto(Product e) {
        ProductDto res = mappingHelper.map(e, ProductDto.class);
        var productDetail = productDetailRepository.findFirstByProduct_Id(e.getId());
        Discount discount = new Discount();
        float price = 0F;
        if (productDetail.isPresent()) {
            discount = productDetail.get().getDiscount();
            price = productDetail.get().getPrice();
        }
        res.setDiscount(discount);
        res.setPrice(price);
        return res;
    }
}
