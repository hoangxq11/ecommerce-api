package com.demo.web.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddToCartReq {
    private Integer productId;
    protected Integer colorId;
    private Integer sizeId;
    private Integer quantity;
}
