package com.demo.repository;

import com.demo.model.OrderType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderType, Integer> {
}
