package com.demo.repository;

import com.demo.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
<<<<<<< HEAD
    boolean findByName(String name);
    boolean findByPhoneNumber(String phoneNumber);

    boolean findByAddress(String address);
=======
>>>>>>> fe19187b42306f21e3065b11c7446f58f6d07ca1
}
