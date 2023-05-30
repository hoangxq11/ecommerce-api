package com.demo.service;

import com.demo.model.Category;
import com.demo.web.dto.CategoryDto;
import com.demo.web.dto.request.CategoryReq;

import java.util.List;

public interface CategoryService {
    List<Category> getCategoriesWithoutParent();

    List<Category> getCategoriesByParentId(Integer parentId);

    Category getCategoriesById(Integer categoryId);

    List<Category> getAllCategories();

    void createCategory(CategoryReq categoryReq);

    void updateCategory(Integer categoryId, CategoryReq categoryReq);

    void deleteCategory(Integer categoryId);
}
