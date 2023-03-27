package com.demo.repository;

import com.demo.model.BillOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillOrderRepository extends JpaRepository<BillOrder, Integer> {
}
