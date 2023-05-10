package com.demo.service;

import com.demo.web.dto.ProductDetailDto;
import com.demo.web.dto.ProductDto;
import com.demo.web.dto.request.CreateCustomProductReq;
import com.demo.web.dto.request.ProductCriteria;
import com.demo.web.dto.request.ProductDetailCriteria;
import com.demo.web.dto.request.ProductDetailReq;

import java.util.List;

public interface ProductService {
    List<ProductDetailDto> getProducts(ProductDetailCriteria productDetailCriteria);

    List<ProductDetailDto> getProductDetailByProductId(Integer productId);

    List<ProductDto> getAllProducts();

    List<ProductDto> getProductsOfCategory(Integer categoryId);

    List<ProductDto> searchProducts(ProductCriteria productCriteria);

    void createCustomProduct(CreateCustomProductReq createCustomProductReq);

    ProductDto getSingleProductById(Integer productId);

    void createProductDetail(ProductDetailReq productDetailReq);

    void updateProductDetail(Integer productDetailId, ProductDetailReq productDetailReq);
}
