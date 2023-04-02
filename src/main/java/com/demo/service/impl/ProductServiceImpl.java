package com.demo.service.impl;

import com.demo.model.Product;
import com.demo.model.ProductDetail;
import com.demo.repository.ProductDetailRepository;
import com.demo.repository.ProductRepository;
import com.demo.service.ProductService;
import com.demo.service.utils.MappingHelper;
import com.demo.web.dto.ProductDetailDto;
import com.demo.web.dto.ProductDto;
import com.demo.web.dto.request.ProductCriteria;
import com.demo.web.exception.EntityNotFoundException;
import com.demo.web.exception.ServiceException;
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

    @Override
    public ProductDetailDto getProductById(Integer productId) {
        var productDetail = productDetailRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException(ProductDetail.class.getName(), productId.toString()));
        return mappingHelper.map(productDetail, ProductDetailDto.class);
    }
    @Override
    public void addProduct(ProductDetailDto productDetailDto){

    }

    @Override
    public void updateProduct(ProductDto productDto, Integer productId){
//        price, image,
        if(productRepository.findById(productId).isPresent()){
            productDto.setId(productId);
            productRepository.save(mappingHelper.map(productDto, Product.class));
        }
        else{
            throw new ServiceException("Unknown error", "err.api.Unknown-error");
        }

    }
    @Override
    public void removeProduct(Integer productId){
//        check cart

//        check bill order p

//        check pill payment

//        check assessment

//        check image
    }
}
