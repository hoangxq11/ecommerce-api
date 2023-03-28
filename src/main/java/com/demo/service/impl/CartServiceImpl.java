package com.demo.service.impl;

import com.demo.model.*;
import com.demo.repository.AccountRepository;
import com.demo.repository.CartRepository;
import com.demo.repository.ProductCartRepository;
import com.demo.repository.ProductDetailRepository;
import com.demo.service.CartService;
import com.demo.service.utils.MappingHelper;
import com.demo.web.dto.ProductCartDto;
import com.demo.web.dto.ProductDetailDto;
import com.demo.web.dto.request.AddToCartReq;
import com.demo.web.exception.EntityNotFoundException;
import com.demo.web.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final ProductCartRepository productCartRepository;
    private final ProductDetailRepository productDetailRepository;
    private final AccountRepository accountRepository;
    private final CartRepository cartRepository;
    private final MappingHelper mappingHelper;

    @Override
    public void addToCart(AddToCartReq addToCartReq) {
        var cart = getCartCurrentUser();
        var productDetail = productDetailRepository.findById(addToCartReq.getProductDetailId())
                .orElseThrow(() ->
                        new EntityNotFoundException(ProductDetail.class.getName(), addToCartReq.getProductDetailId().toString()));
        var productCart = productCartRepository
                .findByCart_IdAndAndProductDetail_Id(cart.getId(), productDetail.getId())
                .orElseGet(() -> ProductCart.builder()
                        .id(new ProductCartKey(cart.getId(), productDetail.getId()))
                        .cart(cart)
                        .productDetail(productDetail)
                        .quantity(0)
                        .build());
        if (addToCartReq.getQuantity() > productDetail.getCountInStock())
            throw new ServiceException("Quantity is greater than count in stock", "");
        productCart.setQuantity(productCart.getQuantity() + addToCartReq.getQuantity());
        productCartRepository.save(productCart);
    }

    @Override
    public List<ProductCartDto> getProductInCart() {
        var cart = getCartCurrentUser();
        return productCartRepository.findByCart_Id(cart.getId()).stream()
                .map(e -> {
                    var productCart = mappingHelper.map(e, ProductCartDto.class);
                    productCart.setProductDetailDto(mappingHelper.map(e.getProductDetail(), ProductDetailDto.class));
                    return productCart;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void removeProductCart(Integer productDetailId) {
        var cart = getCartCurrentUser();
        var productCart = productCartRepository
                .findByCart_IdAndAndProductDetail_Id(cart.getId(), productDetailId)
                .orElseThrow(() -> new ServiceException("Not found product in cart", ""));
        productCartRepository.delete(productCart);
    }

    private Cart getCartCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return cartRepository.findByAccount_Username(username)
                .orElseGet(() -> cartRepository.save(Cart.builder()
                        .createdDate(Instant.now())
                        .account(accountRepository.findOneWithAuthoritiesByUsername(username)
                                .orElseThrow(() -> new EntityNotFoundException(Account.class.getName(), username)))
                        .build()));
    }
}
