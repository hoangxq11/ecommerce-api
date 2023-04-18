package com.demo.web.rest;

import com.demo.service.CategoryService;
import com.demo.web.dto.response.utils.ResponseUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/categories")
@CrossOrigin("*")
public class CategoryResource {
    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<?> getCategoriesWithoutParent(){
        return ResponseUtils.ok(categoryService.getCategoriesWithoutParent());
    }

    @GetMapping("/parent/{parentId}")
    public ResponseEntity<?> getCategoriesByParentId(@PathVariable Integer parentId){
        return ResponseUtils.ok(categoryService.getCategoriesByParentId(parentId));
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<?> getCategoriesById(@PathVariable Integer categoryId){
        return ResponseUtils.ok(categoryService.getCategoriesById(categoryId));
    }

    // TODO: api get search special
    @GetMapping("/special-search")
    public ResponseEntity<?> getSearchSpecial(){
        return null;
    }
}
