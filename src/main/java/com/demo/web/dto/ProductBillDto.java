package com.demo.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductBillDto {
    private Integer id;
    private ProductDetailDto productDetail;
    private Integer quantity;
}
