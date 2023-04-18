package com.demo.service.impl;

import com.demo.model.Product;
import com.demo.repository.CategoryRepository;
import com.demo.repository.ProductDetailRepository;
import com.demo.repository.ProductRepository;
import com.demo.service.ProductService;
import com.demo.service.utils.MappingHelper;
import com.demo.web.dto.ProductDetailDto;
import com.demo.web.dto.ProductDto;
import com.demo.web.dto.request.ProductCriteria;
import com.demo.web.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductDetailRepository productDetailRepository;
    private final CategoryRepository categoryRepository;
    private final MappingHelper mappingHelper;

    @Override
    public List<ProductDetailDto> getProducts(ProductCriteria productCriteria) {
        return productDetailRepository.findAll(productCriteria.toSpecification())
                .stream().map(e ->
                        mappingHelper.map(e, ProductDetailDto.class)
                ).collect(Collectors.toList());
    }

    @Override
    public List<ProductDetailDto> getProductDetailByProductId(Integer productId) {
        return productDetailRepository.findByProduct_Id(productId)
                .stream().map(e -> {
                    var productDetailDto = mappingHelper.map(e, ProductDetailDto.class);
                    productDetailDto.setProductDto(mappingHelper.map(e.getProduct(), ProductDto.class));
                    return productDetailDto;
                }).collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getSpecialProducts() {
        return productRepository.findAll()
                .stream().map(e -> {
                    ProductDto res = mappingHelper.map(e, ProductDto.class);
                    var productDetail = productDetailRepository.findFirstByProduct_Id(e.getId())
                            .orElseThrow(() -> new EntityNotFoundException(Product.class.getName(), e.getId().toString()));
                    res.setDiscount(productDetail.getDiscount());
                    res.setPrice(productDetail.getPrice());
                    return res;
                })
                .filter(e -> e.getDiscount() != null)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getProductsOfCategory(Integer categoryId) {
        List<ProductDto> products = new ArrayList<>();

        productRepository.findByCategory_Id(categoryId)
                .forEach(e -> {
                    ProductDto res = mappingHelper.map(e, ProductDto.class);
                    var productDetail = productDetailRepository.findFirstByProduct_Id(e.getId())
                            .orElseThrow(() -> new EntityNotFoundException(Product.class.getName(), e.getId().toString()));
                    res.setDiscount(productDetail.getDiscount());
                    res.setPrice(productDetail.getPrice());
                    products.add(res);
                });

        categoryRepository.findAllByCategoryParent_Id(categoryId)
                .forEach(e -> products.addAll(getProductsOfCategory(e.getId())));

        return products;
    }
}
