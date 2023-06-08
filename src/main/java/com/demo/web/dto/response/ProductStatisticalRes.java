package com.demo.web.dto.response;

import com.demo.model.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductStatisticalRes {
    private Integer id;
    private String name;
    private Date createdAt;
    private Float price;
    private String categoryName;
    private int value;
}

