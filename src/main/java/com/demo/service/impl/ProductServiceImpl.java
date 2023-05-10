package com.demo.service.impl;

import com.demo.model.*;
import com.demo.repository.*;
import com.demo.service.ProductService;
import com.demo.service.utils.MappingHelper;
import com.demo.web.dto.ProductDetailDto;
import com.demo.web.dto.ProductDto;
import com.demo.web.dto.request.CreateCustomProductReq;
import com.demo.web.dto.request.ProductCriteria;
import com.demo.web.dto.request.ProductDetailCriteria;
import com.demo.web.dto.request.ProductDetailReq;
import com.demo.web.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductDetailRepository productDetailRepository;
    private final CategoryRepository categoryRepository;
    private final MaterialRepository materialRepository;
    private final SupplierRepository supplierRepository;
    private final SizeRepository sizeRepository;
    private final ColorRepository colorRepository;
    private final DiscountRepository discountRepository;
    private final ImageRepository imageRepository;
    private final MappingHelper mappingHelper;

    @Override
    public List<ProductDetailDto> getProducts(ProductDetailCriteria productDetailCriteria) {
        return productDetailRepository.findAll(productDetailCriteria.toSpecification())
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
    public List<ProductDto> getAllProducts() {
        return productRepository.findAll()
                .stream().map(this::mapToProductDto)
                .filter(e -> e.getDiscount() != null).sorted((o1, o2) -> (o2.getId().compareTo(o1.getId()))).collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getProductsOfCategory(Integer categoryId) {
        List<ProductDto> products = new ArrayList<>();

        productRepository.findByCategory_Id(categoryId)
                .forEach(e -> {
                    ProductDto res = mapToProductDto(e);
                    products.add(res);
                });

        categoryRepository.findAllByCategoryParent_Id(categoryId)
                .forEach(e -> products.addAll(getProductsOfCategory(e.getId())));
        products.sort((o1, o2) -> (o2.getId().compareTo(o1.getId())));
        return products;
    }

    @Override
    public List<ProductDto> searchProducts(ProductCriteria productCriteria) {
        return productRepository.findAll(productCriteria.toSpecification())
                .stream().map(this::mapToProductDto)
                .collect(Collectors.toList());
    }

    @Override
    public void createCustomProduct(CreateCustomProductReq createCustomProductReq) {
        var category = categoryRepository.findById(createCustomProductReq.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException(
                        Category.class.getName(),
                        createCustomProductReq.getCategoryId().toString()
                ));
        var product = mappingHelper.map(createCustomProductReq, Product.class);
        product.setCategory(category);
        product.setCreatedAt(new Date());
        productRepository.save(product);

        var images = createCustomProductReq.getImageUUIDs()
                .stream().map(e -> {
                    var image = imageRepository.findById(UUID.fromString(e))
                            .orElseThrow(() -> new EntityNotFoundException(Image.class.getName(), e));
                    image.setProduct(product);
                    imageRepository.save(image);
                    return image;
                }).collect(Collectors.toList());
        product.setImages(images);

        Material material = new Material();
        material.setName(createCustomProductReq.getMaterialStr());
        materialRepository.save(material);
        Supplier supplier = new Supplier();
        supplier.setName(createCustomProductReq.getSupplierStr());
        supplierRepository.save(supplier);

        product.setMaterial(material);
        product.setSupplier(supplier);

        productRepository.save(product);
    }

    @Override
    public ProductDto getSingleProductById(Integer productId) {
        var product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException(Product.class.getName(), productId.toString()));
        return mapToProductDto(product);
    }

    @Override
    public void createProductDetail(ProductDetailReq productDetailReq) {
        var product = productRepository.findById(productDetailReq.getProductId())
                .orElseThrow(() -> new EntityNotFoundException(
                        Product.class.getName(), productDetailReq.getProductId().toString()
                ));
        var productDetail = mappingHelper.map(productDetailReq, ProductDetail.class);
        productDetail.setProduct(product);

        String sizeStr = productDetailReq.getSizeStr().trim();
        if (!sizeStr.isBlank() && !sizeStr.isEmpty()) {
            var size = sizeRepository.save(new Size(sizeStr));
            productDetail.setSize(size);
        }

        String colorStr = productDetailReq.getColorStr().trim();
        if (!colorStr.isBlank() && !colorStr.isEmpty()) {
            var color = colorRepository.save(new Color(colorStr));
            productDetail.setColor(color);
        }

        Integer discountValue = productDetailReq.getDiscountValue();
        if (discountValue > 0) {
            productDetail.setDiscount(discountRepository.save(new Discount(
                    discountValue, productDetailReq.getDiscountEndDate()
            )));
        }
        productDetailRepository.save(productDetail);
    }

    @Override
    public void updateProductDetail(Integer productDetailId, ProductDetailReq productDetailReq) {
        var productDetail = productDetailRepository.findById(productDetailId)
                .orElseThrow(() -> new EntityNotFoundException(
                        ProductDetail.class.getName(), productDetailId.toString()
                ));

        String sizeStr = productDetailReq.getSizeStr().trim();
        if (!sizeStr.isBlank() && !sizeStr.isEmpty()) {
            if (productDetail.getSize() == null) {
                var size = sizeRepository.save(new Size(sizeStr));
                productDetail.setSize(size);
            } else {
                productDetail.getSize().setName(sizeStr);
                sizeRepository.save(productDetail.getSize());
            }
        }

        String colorStr = productDetailReq.getColorStr().trim();
        if (!colorStr.isBlank() && !colorStr.isEmpty()) {
            if (productDetail.getColor() == null) {
                var color = colorRepository.save(new Color(colorStr));
                productDetail.setColor(color);
            } else {
                productDetail.getColor().setName(colorStr);
                colorRepository.save(productDetail.getColor());
            }
        }

        Integer discountValue = productDetailReq.getDiscountValue();
        if (discountValue > 0) {
            if (productDetail.getDiscount() == null) {
                productDetail.setDiscount(discountRepository.save(new Discount(
                        discountValue, productDetailReq.getDiscountEndDate()
                )));
            } else {
                productDetail.getDiscount().setValue(discountValue);
                productDetail.getDiscount().setEndDate(productDetailReq.getDiscountEndDate());
                discountRepository.save(productDetail.getDiscount());
            }
        }
        productDetailRepository.save(productDetail);
    }

    private ProductDto mapToProductDto(Product e) {
        ProductDto res = mappingHelper.map(e, ProductDto.class);
        var productDetail = productDetailRepository.findFirstByProduct_Id(e.getId());
        Discount discount = new Discount();
        float price = 0F;
        if (productDetail.isPresent()) {
            discount = productDetail.get().getDiscount();
            price = productDetail.get().getPrice();
        }
        res.setDiscount(discount);
        res.setPrice(price);
        return res;
    }
}
