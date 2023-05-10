package com.demo.web.rest;

import com.demo.service.AssessmentService;
import com.demo.web.dto.request.AssessmentReq;
import com.demo.web.dto.response.utils.ResponseUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/assessment")
@CrossOrigin("*")
@RequiredArgsConstructor
public class AssessmentResource {

    private final AssessmentService assessmentService;

    @PostMapping
    public ResponseEntity<?> createAssessment(@RequestBody AssessmentReq assessmentReq){
        assessmentService.createAssessment(assessmentReq);
        return ResponseUtils.ok("Created");
    }

    @GetMapping("/check-exist/{productBillId}")
    public ResponseEntity<?> checkExistAssessment(@PathVariable Integer productBillId){
        return ResponseUtils.ok(assessmentService.checkExistAssessment(productBillId));
    }

}
