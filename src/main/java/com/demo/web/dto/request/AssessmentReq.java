package com.demo.web.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class AssessmentReq {
    private String comment;
    private Integer star;
    private List<String> imageIds;
    private Integer productBillId;
}
