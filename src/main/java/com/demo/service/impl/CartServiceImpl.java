package com.demo.service.impl;

import com.demo.model.*;
import com.demo.repository.*;
import com.demo.service.CartService;
import com.demo.service.utils.MappingHelper;
import com.demo.web.dto.ProductCartDto;
import com.demo.web.dto.ProductDetailDto;
import com.demo.web.dto.ProductDto;
import com.demo.web.dto.request.AddToCartReq;
import com.demo.web.dto.request.ProductCartUpdateReq;
import com.demo.web.exception.EntityNotFoundException;
import com.demo.web.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final ProductCartRepository productCartRepository;
    private final ProductDetailRepository productDetailRepository;
    private final AccountRepository accountRepository;
    private final CartRepository cartRepository;
    private final ColorRepository colorRepository;
    private final SizeRepository sizeRepository;
    private final MappingHelper mappingHelper;

    @Override
    public void addToCart(AddToCartReq addToCartReq) {
        var cart = getCartCurrentUser();
        var productDetailList = productDetailRepository.findByProduct_IdAndSize_IdAndColor_Id(
                addToCartReq.getProductId(), addToCartReq.getSizeId(), addToCartReq.getColorId());
        ProductDetail productDetail;

        if (productDetailList.isEmpty()) {
            var productDetailListCheck = productDetailRepository.findByProduct_Id(addToCartReq.getProductId());
            var productDetailCheck = productDetailListCheck.get(new Random().nextInt(productDetailListCheck.size()));
            ProductDetail productDetailNew = new ProductDetail();
            mappingHelper.copyProperties(productDetailCheck, productDetailNew);
            productDetailNew.setId(null);
            productDetailNew.setColor(colorRepository.findById(addToCartReq.getColorId()).get());
            productDetailNew.setSize(sizeRepository.findById(addToCartReq.getSizeId()).get());
            productDetail = productDetailRepository.save(productDetailNew);
        } else productDetail = productDetailList.get(new Random().nextInt(productDetailList.size()));

        var productCart = productCartRepository
                .findByCart_IdAndProductDetail_Id(cart.getId(), productDetail.getId())
                .orElseGet(() -> ProductCart.builder()
                        .id(new ProductCartKey(cart.getId(), productDetail.getId()))
                        .cart(cart)
                        .productDetail(productDetail)
                        .quantity(0)
                        .build());
        if (addToCartReq.getQuantity() > productDetail.getCountInStock())
            throw new ServiceException("Quantity is greater than count in stock", "");
        productCart.setQuantity(productCart.getQuantity() + addToCartReq.getQuantity());
        productCart.setChecked(false);
        productCartRepository.save(productCart);
    }

    @Override
    public List<ProductCartDto> getProductInCart() {
        var cart = getCartCurrentUser();
        return productCartRepository.findByCart_Id(cart.getId()).stream()
                .map(e -> {
                    var productCart = mappingHelper.map(e, ProductCartDto.class);
                    var productDetailDto = mappingHelper.map(e.getProductDetail(), ProductDetailDto.class);
                    productDetailDto.setProductDto(mappingHelper.map(e.getProductDetail().getProduct(), ProductDto.class));
                    productCart.setProductDetailDto(productDetailDto);
                    return productCart;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void removeProductCart(Integer productDetailId) {
        var cart = getCartCurrentUser();
        var productCart = productCartRepository
                .findByCart_IdAndProductDetail_Id(cart.getId(), productDetailId)
                .orElseThrow(() -> new ServiceException("Not found product in cart", ""));
        productCartRepository.delete(productCart);
    }

    @Override
    public ProductCartDto updateProductCart(ProductCartUpdateReq productCartUpdateReq) {
        var cart = getCartCurrentUser();
        var productDetail = productDetailRepository.findById(productCartUpdateReq.getProductDetailId())
                .orElseThrow(() ->
                        new EntityNotFoundException(ProductDetail.class.getName(),
                                productCartUpdateReq.getProductDetailId().toString()));
        var productCart = productCartRepository
                .findByCart_IdAndProductDetail_Id(cart.getId(), productDetail.getId())
                .orElseThrow(() -> new EntityNotFoundException(ProductCart.class.getName(),
                        cart.getId().toString() + "-" + productDetail.getId().toString()));
        if (productCartUpdateReq.getQuantity() > productDetail.getCountInStock())
            throw new ServiceException("Quantity is greater than count in stock", "");
        productCart.setQuantity(productCartUpdateReq.getQuantity());
        if (productCartUpdateReq.getChecked() != null)
            productCart.setChecked(productCartUpdateReq.getChecked());
        return mapProductCartDto(productCart);
    }

    @Override
    public ProductCartDto updateCheckToPayment(Integer productDetailId) {
        var cart = getCartCurrentUser();
        var productDetail = productDetailRepository.findById(productDetailId)
                .orElseThrow(() ->
                        new EntityNotFoundException(ProductDetail.class.getName(),
                                productDetailId.toString()));
        var productCart = productCartRepository
                .findByCart_IdAndProductDetail_Id(cart.getId(), productDetail.getId())
                .orElseThrow(() -> new EntityNotFoundException(ProductCart.class.getName(),
                        cart.getId().toString() + "-" + productDetail.getId().toString()));
        productCart.setChecked(!productCart.getChecked());
        return mapProductCartDto(productCart);
    }

    private ProductCartDto mapProductCartDto(ProductCart productCart) {
        var productCartRes = productCartRepository.save(productCart);
        var productCartDto = mappingHelper.map(productCartRes, ProductCartDto.class);
        var productDetailDto = mappingHelper.map(productCartRes.getProductDetail(), ProductDetailDto.class);
        productDetailDto.setProductDto(mappingHelper.map(productCartRes.getProductDetail().getProduct(), ProductDto.class));
        productCartDto.setProductDetailDto(productDetailDto);
        return productCartDto;
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
