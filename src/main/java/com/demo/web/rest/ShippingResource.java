package com.demo.web.rest;

import com.demo.service.ShippingLayerService;
import com.demo.web.dto.response.utils.ResponseUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/shipping-services")
@CrossOrigin("*")
@RequiredArgsConstructor
public class ShippingResource {
    private final ShippingLayerService shippingLayerService;

    @GetMapping
    public ResponseEntity<?> getAllShippingService(){
        return ResponseUtils.ok(shippingLayerService.getAllShippingService());
    }
}
