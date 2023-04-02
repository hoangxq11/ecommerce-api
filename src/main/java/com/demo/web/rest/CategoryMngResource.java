package com.demo.web.rest;

import com.demo.service.CategoryService;
import com.demo.web.dto.CategoryDto;
import com.demo.web.dto.response.utils.ResponseUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/admin/category")
@RequiredArgsConstructor
public class CategoryMngResource {
    private final CategoryService categoryService;

    @PostMapping("/add-category")
    public ResponseEntity<?> addCategory(@RequestBody CategoryDto categoryDto){
        categoryService.addCategory(categoryDto);
        return ResponseUtils.created();
    }

    @DeleteMapping("/delete/{categoryId}")
    public ResponseEntity<?> removeCategory(@PathVariable Integer categoryId){
        categoryService.removeCategory(categoryId);
        return ResponseUtils.ok("Removed");
    }
}
