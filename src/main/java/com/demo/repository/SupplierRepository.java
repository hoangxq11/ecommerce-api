package com.demo.repository;

import com.demo.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
    boolean findByName(String name);
    boolean findByPhoneNumber(String phoneNumber);

    boolean findByAddress(String address);
}
