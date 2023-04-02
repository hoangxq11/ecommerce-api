package com.demo.service.impl;

import com.demo.model.Account;
import com.demo.model.Address;
import com.demo.repository.AccountRepository;
import com.demo.repository.AddressRepository;
import com.demo.service.AddressService;
import com.demo.service.utils.MappingHelper;
import com.demo.web.dto.AddressDto;
import com.demo.web.dto.request.AddressReq;
import com.demo.web.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final AccountRepository accountRepository;
    private final MappingHelper mappingHelper;

    @Override
    public AddressDto getDefaultAddressCurrentAccount() {
        return mappingHelper.map(addressRepository
                        .findByAccount_UsernameAndDefaultAddress(getCurrentUsernameAccount(), true),
                AddressDto.class);
    }

    @Override
    public List<AddressDto> getAllAddressCurrentAccount() {
        return mappingHelper.mapList(addressRepository
                .findByAccount_Username(getCurrentUsernameAccount()), AddressDto.class);
    }

    @Override
    public void createAddressCurrentAccount(AddressReq addressReq) {
        String username = getCurrentUsernameAccount();
        Account account = accountRepository.findOneWithAuthoritiesByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException(Account.class.getName(), username));
        Address address = mappingHelper.map(addressReq, Address.class);
        address.setAccount(account);
        if (addressRepository.count() == 0) address.setDefaultAddress(true);
        else if (addressReq.getDefaultAddress().equals(true)) {
            Address addressDefault = addressRepository.findByAccount_UsernameAndDefaultAddress(
                    username, true
            );
            addressDefault.setDefaultAddress(false);
            addressRepository.save(addressDefault);
        }
        addressRepository.save(address);
    }

    @Override
    public void updateAddress(Integer addressId, AddressReq addressReq) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new EntityNotFoundException(Address.class.getName(), addressId.toString()));
        if (address.getDefaultAddress().equals(false) && addressReq.getDefaultAddress().equals(true)) {
            Address addressDefault = addressRepository.findByAccount_UsernameAndDefaultAddress(
                    getCurrentUsernameAccount(), true
            );
            addressDefault.setDefaultAddress(false);
            addressRepository.save(addressDefault);
            mappingHelper.mapIfSourceNotNullAndStringNotBlank(addressReq, address);
            addressRepository.save(address);
        } else if (address.getDefaultAddress().equals(true) && addressReq.getDefaultAddress().equals(false)) {
            mappingHelper.mapIfSourceNotNullAndStringNotBlank(addressReq, address);
            address.setDefaultAddress(true);
            addressRepository.save(address);
        } else {
            mappingHelper.mapIfSourceNotNullAndStringNotBlank(addressReq, address);
            addressRepository.save(address);
        }
    }

    @Override
    public void removeAddress(Integer addressId) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new EntityNotFoundException(Address.class.getName(), addressId.toString()));
        addressRepository.delete(address);
    }

    private String getCurrentUsernameAccount() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
