package com.demo.web.dto.request;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CreateCustomProductReq {
    private String name;
    private String description;
    private Integer categoryId;
    private String materialStr;
    private String supplierStr;
    private List<String> imageUUIDs;
}
