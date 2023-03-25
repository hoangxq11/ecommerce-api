package com.demo.service.impl;

import com.demo.repository.ProductDetailRepository;
import com.demo.repository.ProductRepository;
import com.demo.service.ProductService;
import com.demo.service.utils.MappingHelper;
import com.demo.web.dto.ProductDetailDto;
import com.demo.web.dto.request.ProductCriteria;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductDetailRepository productDetailRepository;
    private final MappingHelper mappingHelper;

    @Override
    public List<ProductDetailDto> getProducts(ProductCriteria productCriteria) {
        return productDetailRepository.findAll(productCriteria.toSpecification())
                .stream().map(e ->
                        mappingHelper.map(e, ProductDetailDto.class)
                ).collect(Collectors.toList());
    }
}
