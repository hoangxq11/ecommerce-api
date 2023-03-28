package com.demo.service;

import com.demo.web.dto.ProductCartDto;
import com.demo.web.dto.request.AddToCartReq;

import java.util.List;

public interface CartService {
    void addToCart(AddToCartReq addToCartReq);

    List<ProductCartDto> getProductInCart();

    void removeProductCart(Integer productDetailId);
}
