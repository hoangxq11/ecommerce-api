package com.demo.repository;

import com.demo.model.ShippingService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShippingServiceRepository extends JpaRepository<ShippingService, Integer> {
}
