package com.demo.web.dto.request;

import lombok.Data;

@Data
public class BillStatusUpdateReq {
    private Integer billId;
    private String status;
}
