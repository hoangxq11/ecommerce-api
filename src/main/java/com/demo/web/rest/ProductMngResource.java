package com.demo.web.rest;

import com.demo.service.ProductService;
import com.demo.service.SupplierService;
import com.demo.web.dto.ProductDetailDto;
import com.demo.web.dto.ProductDto;
import com.demo.web.dto.SupplierDto;
import com.demo.web.dto.response.utils.ResponseUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/admin/product")
@RequiredArgsConstructor
public class ProductMngResource {
    
    private final SupplierService supplierService;

    private final ProductService productService;
    @PostMapping("/add-product")
    public ResponseEntity<?> addProduct(@RequestBody ProductDetailDto productDetailDto){
        productService.addProduct(productDetailDto);
        return ResponseUtils.created();
    }
    @PostMapping("/update/{productId}")
    public ResponseEntity<?> updateProduct(@RequestBody ProductDto productDto, @PathVariable Integer productId){
        productService.updateProduct(productDto, productId);
        return ResponseUtils.ok();
    }
    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<?> removeProductCart(@PathVariable Integer productId){
        productService.removeProduct(productId);
        return ResponseUtils.ok("Removed");
    }
}
