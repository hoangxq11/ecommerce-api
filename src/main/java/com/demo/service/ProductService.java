package com.demo.service;

import com.demo.web.dto.ProductDetailDto;
import com.demo.web.dto.ProductDto;
import com.demo.web.dto.request.ProductCriteria;

import java.util.List;

public interface ProductService {
    List<ProductDetailDto> getProducts(ProductCriteria productCriteria);

    ProductDetailDto getProductById(Integer productId);

    void addProduct(ProductDetailDto productDetailDto);

    void updateProduct(ProductDto productDto, Integer productId);

    void removeProduct(Integer productId);

}
