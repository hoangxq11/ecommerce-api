package com.demo.service.impl;

import com.demo.model.Supplier;
import com.demo.repository.SupplierRepository;
import com.demo.service.SupplierService;
import com.demo.service.utils.MappingHelper;
import com.demo.web.dto.StoreDto;
import com.demo.web.dto.SupplierDto;
import com.demo.web.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {
    private final SupplierRepository supplierRepository;
    private final MappingHelper mappingHelper;

    @Override
    public void addSupplier(SupplierDto supplierDto) {
        if(supplierRepository.findByName(supplierDto.getName()) && supplierRepository.findByPhoneNumber(supplierDto.getPhoneNumber()) && supplierRepository.findByAddress(supplierDto.getAddress())){
            throw new ServiceException("Supplier is existed in system", "err.api.supplier-is-existed");
        }
        Supplier supplier = mappingHelper.map(supplierDto, Supplier.class);
        supplierRepository.save(supplier);
    }
}
