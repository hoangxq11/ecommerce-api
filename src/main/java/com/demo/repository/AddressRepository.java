package com.demo.repository;

import com.demo.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Integer> {
    List<Address> findByAccount_Username(String username);
    Optional<Address> findByAccount_UsernameAndDefaultAddress(String username, boolean defaultAddress);
}
