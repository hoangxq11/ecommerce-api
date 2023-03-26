package com.demo.service.impl;

import com.demo.repository.CategoryRepository;
import com.demo.service.CategoryService;
import com.demo.service.utils.MappingHelper;
import com.demo.web.dto.CategoryDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final MappingHelper mappingHelper;

    @Override
    public List<CategoryDto> getCategoriesWithoutParent() {
        return categoryRepository.findAllByCategoryParentIsNull().stream()
                .map(e -> mappingHelper.map(e, CategoryDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<CategoryDto> getCategoriesByParentId(Integer parentId) {
        return categoryRepository.findAllByCategoryParent_Id(parentId)
                .stream().map(e -> mappingHelper.map(e, CategoryDto.class))
                .collect(Collectors.toList());
    }
}
