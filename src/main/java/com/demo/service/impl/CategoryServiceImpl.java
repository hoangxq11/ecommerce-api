package com.demo.service.impl;

import com.demo.model.Category;
import com.demo.repository.CategoryRepository;
import com.demo.service.CategoryService;
import com.demo.service.utils.MappingHelper;
import com.demo.web.dto.CategoryDto;
import com.demo.web.exception.ServiceException;
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

    @Override
    public void addCategory(CategoryDto categoryDto){
        if (categoryRepository.findByName(categoryDto.getName())){
            throw new ServiceException("Category is existed", "err.api.category-is-existed");
        }
        categoryRepository.save(mappingHelper.map(categoryDto, Category.class));
    }
    @Override
    public void removeCategory(Integer categoryId){
        if(categoryRepository.findAllByCategoryParent_Id(categoryId).isEmpty()){
            categoryRepository.deleteById(categoryId);
        }
        else {
            throw new ServiceException("Category cant delete, it is connection", "err.api.category-cant-delete");
        }
    }
}
