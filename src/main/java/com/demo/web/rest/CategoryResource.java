package com.demo.web.rest;

import com.demo.service.CategoryService;
import com.demo.web.dto.request.CategoryReq;
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
    public ResponseEntity<?> getCategoriesWithoutParent() {
        return ResponseUtils.ok(categoryService.getCategoriesWithoutParent());
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllCategories() {
        return ResponseUtils.ok(categoryService.getAllCategories());
    }

    @GetMapping("/parent/{parentId}")
    public ResponseEntity<?> getCategoriesByParentId(@PathVariable Integer parentId) {
        return ResponseUtils.ok(categoryService.getCategoriesByParentId(parentId));
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<?> getCategoriesById(@PathVariable Integer categoryId) {
        return ResponseUtils.ok(categoryService.getCategoriesById(categoryId));
    }

    @PostMapping()
    public ResponseEntity<?> createCategory(@RequestBody CategoryReq categoryReq) {
        categoryService.createCategory(categoryReq);
        return ResponseUtils.ok("Created");
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<?> updateCategory(@RequestBody CategoryReq categoryReq, @PathVariable Integer categoryId) {
        categoryService.updateCategory(categoryId, categoryReq);
        return ResponseUtils.ok("Updated");
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable Integer categoryId) {
        categoryService.deleteCategory(categoryId);
        return ResponseUtils.ok("Updated");
    }

    // TODO: api get search special
    @GetMapping("/special-search")
    public ResponseEntity<?> getSearchSpecial() {
        return null;
    }
}
