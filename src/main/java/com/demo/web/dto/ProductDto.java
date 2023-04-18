package com.demo.web.dto;

import com.demo.model.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Integer id;
    private String name;
    private String description;
    private Date createdAt;
    private Float price;
    private Category category;
    private Material material;
    private Supplier supplier;
    private Discount discount;
    List<Image> images;
}
