package com.demo.repository;

import com.demo.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> findAllByCategoryParentIsNull();
    List<Category> findAllByCategoryParent_Id(Integer parentId);
}
