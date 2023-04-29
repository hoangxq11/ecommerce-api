package com.demo.web.rest;

import com.demo.service.CartService;
import com.demo.web.dto.request.AddToCartReq;
import com.demo.web.dto.request.ProductCartUpdateReq;
import com.demo.web.dto.response.utils.ResponseUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/me/cart")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CartResource {
    private final CartService cartService;

    @PostMapping
    public ResponseEntity<?> addToCart(@RequestBody AddToCartReq addToCartReq) {
        cartService.addToCart(addToCartReq);
        return ResponseUtils.created();
    }

    @PutMapping
    public ResponseEntity<?> updateProductCart(@RequestBody ProductCartUpdateReq productCartUpdateReq) {
        return ResponseUtils.ok("Updated", cartService.updateProductCart(productCartUpdateReq));
    }

    @PostMapping("/check-to-payment/{productDetailId}")
    public ResponseEntity<?> updateCheckToPayment(@PathVariable Integer productDetailId) {
        return ResponseUtils.ok("Updated", cartService.updateCheckToPayment(productDetailId));
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
