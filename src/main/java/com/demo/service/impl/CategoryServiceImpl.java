package com.demo.service.impl;

import com.demo.model.Category;
import com.demo.repository.CategoryRepository;
import com.demo.service.CategoryService;
import com.demo.service.utils.MappingHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final MappingHelper mappingHelper;

    @Override
    public List<Category> getCategoriesWithoutParent() {
        return new ArrayList<>(categoryRepository.findAllByCategoryParentIsNull());
    }

    @Override
    public List<Category> getCategoriesByParentId(Integer parentId) {
        return new ArrayList<>(categoryRepository.findAllByCategoryParent_Id(parentId));
    }
}
