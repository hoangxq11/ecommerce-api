package com.demo.repository;

import com.demo.model.ProductBill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductBillRepository extends JpaRepository<ProductBill, Integer> {
}
