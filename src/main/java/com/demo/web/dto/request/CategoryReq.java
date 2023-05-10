package com.demo.web.dto.request;

import lombok.Data;

@Data
public class CategoryReq {
    private Integer categoryParentId;
    private String name;
    private String imageId;
}
