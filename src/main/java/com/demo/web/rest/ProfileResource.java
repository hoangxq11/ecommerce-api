package com.demo.web.rest;

import com.demo.service.ProfileService;
import com.demo.web.dto.response.utils.ResponseUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/me/profile")
@RequiredArgsConstructor
public class ProfileResource {
    private final ProfileService profileService;

    @GetMapping("/customer-profile")
    public ResponseEntity<?> getCustomerProfile(){
        return ResponseUtils.ok(profileService.getCustomerProfile());
    }
}
