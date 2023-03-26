package com.demo.service;

import com.demo.web.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> getCategoriesWithoutParent();

    List<CategoryDto> getCategoriesByParentId(Integer parentId);
}
