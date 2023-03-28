package com.demo.web.rest;

import com.demo.service.CartService;
import com.demo.web.dto.request.AddToCartReq;
import com.demo.web.dto.response.utils.ResponseUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CartResource {
    private final CartService cartService;

    @PostMapping
    public ResponseEntity<?> addToCart(@RequestBody AddToCartReq addToCartReq) {
        cartService.addToCart(addToCartReq);
        return ResponseUtils.created();
    }

    @GetMapping
    public ResponseEntity<?> getProductInCart() {
        return ResponseUtils.ok(cartService.getProductInCart());
    }

    @DeleteMapping("/{productDetailId}")
    public ResponseEntity<?> removeProductCart(@PathVariable Integer productDetailId){
        cartService.removeProductCart(productDetailId);
        return ResponseUtils.ok("Removed");
    }

}
