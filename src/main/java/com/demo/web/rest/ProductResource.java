package com.demo.web.rest;

import com.demo.repository.ImageRepository;
import com.demo.service.ProductService;
import com.demo.web.dto.request.ProductCriteria;
import com.demo.web.dto.response.utils.ResponseUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
@CrossOrigin("*")
@Slf4j
public class ProductResource {
    private final ProductService productService;
    private final ImageRepository imageRepository;

    @PostMapping
    public ResponseEntity<?> getProducts(@RequestBody ProductCriteria productCriteria) {
        return ResponseUtils.ok(productService.getProducts(productCriteria));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<?> getProductById(@PathVariable Integer productId) {
        return ResponseUtils.ok(productService.getProductById(productId));
    }

}
