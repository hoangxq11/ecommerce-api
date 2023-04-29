package com.demo.web.dto;

import lombok.Data;

@Data
public class ProductCartDto {
    private ProductDetailDto productDetailDto;
    private int quantity;
    private Boolean checked;
}
