package com.demo.web.dto.request;

import lombok.Data;

@Data
public class ProductCartUpdateReq {
    private Integer productDetailId;
    private Integer quantity;
    private Boolean checked;
}
