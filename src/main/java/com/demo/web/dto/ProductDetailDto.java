package com.demo.web.dto;

import com.demo.model.Color;
import com.demo.model.Discount;
import com.demo.model.Product;
import com.demo.model.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProductDetailDto {
    private Integer id;
    private float price;
    private int countInStock;
    private ProductDto productDto;
    private Discount discount;
    private Color color;
    private Size size;
}
