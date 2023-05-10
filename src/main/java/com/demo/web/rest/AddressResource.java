package com.demo.web.rest;

import com.demo.service.AddressService;
import com.demo.web.dto.request.AddressReq;
import com.demo.web.dto.response.utils.ResponseUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/me/address")
@CrossOrigin("*")
@RequiredArgsConstructor
public class AddressResource {
    private final AddressService addressService;

    @GetMapping("/default")
    public ResponseEntity<?> getDefaultAddressCurrentAccount() {
        return ResponseUtils.ok(addressService.getDefaultAddressCurrentAccount());
    }

    @GetMapping("/{addressId}")
    public ResponseEntity<?> getAddressCurrentAccountByAddressId(@PathVariable Integer addressId) {
        return ResponseUtils.ok(addressService.getAddressCurrentAccountByAddressId(addressId));
    }

    @GetMapping
    public ResponseEntity<?> getAllAddressCurrentAccount() {
        return ResponseUtils.ok(addressService.getAllAddressCurrentAccount());
    }

    @PostMapping
    public ResponseEntity<?> createAddressCurrentAccount(@RequestBody @Valid AddressReq addressReq){
        addressService.createAddressCurrentAccount(addressReq);
        return ResponseUtils.created();
    }

    @PutMapping("/{addressId}")
    public ResponseEntity<?> updateAddress(@PathVariable Integer addressId, @RequestBody AddressReq addressReq) {
        addressService.updateAddress(addressId, addressReq);
        return ResponseUtils.ok("Updated");
    }

    @DeleteMapping("/{addressId}")
    public ResponseEntity<?> removeAddress(@PathVariable Integer addressId){
        addressService.removeAddress(addressId);
        return ResponseUtils.ok("Removed");
    }
}
