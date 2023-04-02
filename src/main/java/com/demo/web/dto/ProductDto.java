package com.demo.web.dto;

import com.demo.model.Category;
import com.demo.model.Image;
import com.demo.model.Material;
import com.demo.model.Supplier;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProductDto {
    private Integer id;
    private String name;
    private String description;
    private Date createdAt;
    private Category category;
    private Material material;
    private Supplier supplier;
    List<Image> images;
}
