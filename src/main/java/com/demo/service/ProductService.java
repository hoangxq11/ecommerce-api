package com.demo.service;

import com.demo.web.dto.ProductDetailDto;
import com.demo.web.dto.request.ProductCriteria;

import java.util.List;

public interface ProductService {
    List<ProductDetailDto> getProducts(ProductCriteria productCriteria);

    ProductDetailDto getProductById(Integer productId);
}
