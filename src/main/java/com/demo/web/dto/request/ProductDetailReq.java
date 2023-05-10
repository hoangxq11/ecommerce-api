package com.demo.web.dto.request;

import lombok.Data;

import java.util.Date;

@Data
public class ProductDetailReq {
    private float price;
    private int countInStock;
    private Integer productId;
    private Integer discountValue;
    private Date discountEndDate;
    private String colorStr;
    private String sizeStr;
}
