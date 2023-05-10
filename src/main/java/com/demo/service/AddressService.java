package com.demo.service;

import com.demo.web.dto.AddressDto;
import com.demo.web.dto.request.AddressReq;

import java.util.List;

public interface AddressService {
    AddressDto getDefaultAddressCurrentAccount();

    List<AddressDto> getAllAddressCurrentAccount();

    void createAddressCurrentAccount(AddressReq addressReq);

    void updateAddress(Integer addressId, AddressReq addressReq);

    void removeAddress(Integer addressId);

    AddressDto getAddressCurrentAccountByAddressId(Integer addressId);
}
