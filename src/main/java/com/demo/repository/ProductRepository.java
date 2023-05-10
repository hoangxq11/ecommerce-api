package com.demo.repository;

import com.demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product> {
    List<Product> findByCategory_Id(Integer categoryId);

    @Query(value = "SELECT * FROM products WHERE created_at Like %?1%", nativeQuery = true)
    List<Product> findByCreatedAtByYear(@Param("createdAt") String eventDate);
}
