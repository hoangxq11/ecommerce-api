package com.demo.repository;

import com.demo.model.Material;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MaterialRepository extends JpaRepository<Material, Integer> {
    boolean findByName(String name);

    @Override
    Optional<Material> findById(Integer integer);
}
