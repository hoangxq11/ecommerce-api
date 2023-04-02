package com.demo.repository;

import com.demo.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Integer> {
    List<Address> findByAccount_Username(String username);
    Address findByAccount_UsernameAndDefaultAddress(String username, boolean defaultAddress);
}
