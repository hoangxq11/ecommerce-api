package com.demo.web.rest.admin;

import com.demo.service.AccountService;
import com.demo.web.dto.response.utils.ResponseUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/accounts")
@CrossOrigin("*")
@RequiredArgsConstructor
public class AccountResource {
    private final AccountService accountService;

    @GetMapping
    public ResponseEntity<?> getAllAccount () {
        return ResponseUtils.ok(accountService.getAllAccountProfiles());
    }

}
