package com.demo.service;

import com.demo.web.dto.request.AssessmentReq;

public interface AssessmentService {
    void createAssessment(AssessmentReq assessmentReq);
    Boolean checkExistAssessment (Integer productBillId);
}
