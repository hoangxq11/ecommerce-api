package com.demo.web.rest;

import com.demo.service.SupplierService;
import com.demo.web.dto.SupplierDto;
import com.demo.web.dto.response.utils.ResponseUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/admin/supplier")
@RequiredArgsConstructor
public class SupplierMngResource {
    private final SupplierService supplierService;
    @PostMapping("/add-product")
    public ResponseEntity<?> addSupplier(@RequestBody SupplierDto supplierDto){
        supplierService.addSupplier(supplierDto);
        return ResponseUtils.created();
    }

}
