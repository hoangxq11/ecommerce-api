package com.demo.service.impl;

import com.demo.model.Category;
import com.demo.model.Image;
import com.demo.repository.CategoryRepository;
import com.demo.repository.ImageRepository;
import com.demo.service.CategoryService;
import com.demo.web.dto.request.CategoryReq;
import com.demo.web.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ImageRepository imageRepository;

    @Override
    public List<Category> getCategoriesWithoutParent() {
        return categoryRepository.findAllByCategoryParentIsNull();
    }

    @Override
    public List<Category> getCategoriesByParentId(Integer parentId) {
        return categoryRepository.findAllByCategoryParent_Id(parentId);
    }

    @Override
    public Category getCategoriesById(Integer categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException(Category.class.getName(), categoryId.toString()));
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public void createCategory(CategoryReq categoryReq) {
        Category category = new Category();
        category.setName(categoryReq.getName());
        if (categoryReq.getCategoryParentId() != null) {
            category.setCategoryParent(categoryRepository.findById(categoryReq.getCategoryParentId())
                    .orElseThrow(() -> new EntityNotFoundException(
                            Category.class.getName(), categoryReq.getCategoryParentId().toString()
                    )));
        }
        if (!categoryReq.getImageId().isBlank()) {
            category.setImage(imageRepository.findById(UUID.fromString(categoryReq.getImageId()))
                    .orElseThrow(() -> new EntityNotFoundException(
                            Image.class.getName(), categoryReq.getImageId()
                    )));
        }
        categoryRepository.save(category);
    }
}
