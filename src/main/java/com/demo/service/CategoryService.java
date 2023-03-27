package com.demo.service;

import com.demo.model.Category;
import com.demo.web.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    List<Category> getCategoriesWithoutParent();

    List<Category> getCategoriesByParentId(Integer parentId);
}
