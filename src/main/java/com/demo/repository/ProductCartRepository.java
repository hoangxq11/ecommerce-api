package com.demo.repository;

import com.demo.model.ProductCart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductCartRepository extends JpaRepository<ProductCart, Integer> {
    Optional<ProductCart> findByCart_IdAndProductDetail_Id(Integer cartId, Integer productDetailId);
    List<ProductCart> findByCart_Id(Integer cartId);
    List<ProductCart> findByCart_IdAndChecked(Integer cartId, Boolean checked);
}
