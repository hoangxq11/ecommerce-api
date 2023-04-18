package com.demo.web.rest;

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

    @GetMapping("/special-products")
    public ResponseEntity<?> getSpecialProducts (){
        return ResponseUtils.ok(productService.getSpecialProducts());
    }

    @PostMapping
    public ResponseEntity<?> getProducts(@RequestBody ProductCriteria productCriteria) {
        return ResponseUtils.ok(productService.getProducts(productCriteria));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<?> getProductDetail(@PathVariable Integer productId) {
        return ResponseUtils.ok(productService.getProductDetailByProductId(productId));
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<?> getProductsOfCategory(@PathVariable Integer categoryId){
        return ResponseUtils.ok(productService.getProductsOfCategory(categoryId));
    }

}
