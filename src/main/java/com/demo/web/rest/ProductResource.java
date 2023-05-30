package com.demo.web.rest;

import com.demo.service.ProductService;
import com.demo.web.dto.request.CreateCustomProductReq;
import com.demo.web.dto.request.ProductCriteria;
import com.demo.web.dto.request.ProductDetailCriteria;
import com.demo.web.dto.request.ProductDetailReq;
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
    public ResponseEntity<?> getSpecialProducts() {
        return ResponseUtils.ok(productService.getSpecialProducts());
    }

    @GetMapping
    public ResponseEntity<?> getAllProducts() {
        return ResponseUtils.ok(productService.getAllProducts());
    }

    @PostMapping
    public ResponseEntity<?> getProducts(@RequestBody ProductDetailCriteria productDetailCriteria) {
        return ResponseUtils.ok(productService.getProducts(productDetailCriteria));
    }

    @PostMapping("/search")
    public ResponseEntity<?> searchProducts(@RequestBody ProductCriteria productCriteria) {
        return ResponseUtils.ok(productService.searchProducts(productCriteria));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<?> getProductDetail(@PathVariable Integer productId) {
        return ResponseUtils.ok(productService.getProductDetailByProductId(productId));
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<?> getProductsOfCategory(@PathVariable Integer categoryId) {
        return ResponseUtils.ok(productService.getProductsOfCategory(categoryId));
    }

    @PostMapping("/create-product")
    public ResponseEntity<?> createCustomProduct(@RequestBody CreateCustomProductReq createCustomProductReq) {
        productService.createCustomProduct(createCustomProductReq);
        return ResponseUtils.ok("Created");
    }

    @PutMapping("/{productId}")
    public ResponseEntity<?> updateProduct(@RequestBody CreateCustomProductReq productReq, @PathVariable Integer productId) {
        productService.updateProduct(productId, productReq);
        return ResponseUtils.ok("Updated");
    }

    @PostMapping("/create-product-detail")
    public ResponseEntity<?> createProductDetail(@RequestBody ProductDetailReq productDetailReq) {
        productService.createProductDetail(productDetailReq);
        return ResponseUtils.ok("Created");
    }

    @PutMapping("/update-product-detail/{productDetailId}")
    public ResponseEntity<?> updateProductDetail(@RequestBody ProductDetailReq productDetailReq, @PathVariable Integer productDetailId) {
        productService.updateProductDetail(productDetailId, productDetailReq);
        return ResponseUtils.ok("Updated");
    }

    @GetMapping("/single-product/{productId}")
    public ResponseEntity<?> getSingleProductById(@PathVariable Integer productId) {
        return ResponseUtils.ok(productService.getSingleProductById(productId));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer productId) {
        productService.deleteProduct(productId);
        return ResponseUtils.ok("Updated");
    }

}
