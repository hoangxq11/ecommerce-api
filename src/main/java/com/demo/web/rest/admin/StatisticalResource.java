package com.demo.web.rest.admin;

import com.demo.service.StatisticalService;
import com.demo.web.dto.response.utils.ResponseUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/statistical")
@CrossOrigin("*")
@RequiredArgsConstructor
public class StatisticalResource {
    private final StatisticalService statisticalService;

    @GetMapping("/{year}")
    public ResponseEntity<?> salesByYear(@PathVariable String year){
        return ResponseUtils.ok(statisticalService.salesByYear(year));
    }

    @GetMapping("/best-customer/{year}")
    public ResponseEntity<?> bestCustomer(@PathVariable String year){
        return ResponseUtils.ok(statisticalService.bestCustomer(year));
    }

}
