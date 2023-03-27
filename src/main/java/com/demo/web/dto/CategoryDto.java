package com.demo.web.dto;

import lombok.Data;

@Data
public class CategoryDto {
    private Integer id;
    private CategoryDto categoryParent;
    private String name;
}
