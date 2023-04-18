package com.demo.repository;

import com.demo.model.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface ProductDetailRepository extends JpaRepository<ProductDetail, Integer>, JpaSpecificationExecutor<ProductDetail> {
    Optional<ProductDetail> findFirstByProduct_Id(Integer productId);
    List<ProductDetail> findByProduct_Id(Integer productId);
}
