package com.demo.web.rest;

import com.demo.service.ProductService;
import com.demo.web.dto.request.ProductCriteria;
import com.demo.web.dto.response.utils.ResponseUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
@CrossOrigin("*")
public class ProductResource {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<?> getProducts(@RequestBody ProductCriteria productCriteria) {
        return ResponseUtils.ok(productService.getProducts(productCriteria));
    }
}
