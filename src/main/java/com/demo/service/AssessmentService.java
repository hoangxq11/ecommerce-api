package com.demo.service;

import com.demo.web.dto.AssessmentDto;
import com.demo.web.dto.request.AssessmentReq;

import java.util.List;

public interface AssessmentService {
    void createAssessment(AssessmentReq assessmentReq);
    Boolean checkExistAssessment (Integer productBillId);

    List<AssessmentDto> getAssessmentOfProduct(Integer productId);
}
