package com.demo.web.rest;

import com.demo.service.ProductService;
import com.demo.service.ProfileService;
import com.demo.service.StoreService;
import com.demo.web.dto.request.ProductCriteria;
import com.demo.web.dto.response.utils.ResponseUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("api/admin")
@RequiredArgsConstructor

public class AdminController {
    private final ProductService productService;
    private final ProfileService profileService;
    private final StoreService storeService;

    @GetMapping("/admin-profile")
    public ResponseEntity<?> getAdminProfile(){
        return ResponseUtils.ok(profileService.getCustomerProfile());
    }

    @GetMapping("/store-profile")
    public ResponseEntity<?> getStoreProfile(){ return ResponseUtils.ok(storeService.getStoreProfile(1));}

    @GetMapping("/products")
    public ResponseEntity<?> getProducts(@RequestBody ProductCriteria productCriteria) {
        return ResponseUtils.ok(productService.getProducts(productCriteria));
    }

    @GetMapping("/staff")
    public ResponseEntity<?> getStaff(){ return ResponseUtils.ok(profileService.getStaff());}

    @GetMapping("/order")
    public ResponseEntity<?> getOrder(){ return ResponseUtils.ok();}

//    @GetMapping("/statistic")
}
