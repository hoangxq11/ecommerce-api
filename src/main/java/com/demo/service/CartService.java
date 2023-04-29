package com.demo.service;

import com.demo.web.dto.ProductCartDto;
import com.demo.web.dto.request.AddToCartReq;
import com.demo.web.dto.request.ProductCartUpdateReq;

import java.util.List;

public interface CartService {
    void addToCart(AddToCartReq addToCartReq);

    List<ProductCartDto> getProductInCart();

    void removeProductCart(Integer productDetailId);

    ProductCartDto updateProductCart(ProductCartUpdateReq productCartUpdateReq);

    ProductCartDto updateCheckToPayment(Integer productDetailId);
}
