package com.demo.service.impl;

import com.demo.model.Account;
import com.demo.model.Assessment;
import com.demo.model.Image;
import com.demo.model.ProductBill;
import com.demo.repository.AccountRepository;
import com.demo.repository.AssessmentRepository;
import com.demo.repository.ImageRepository;
import com.demo.repository.ProductBillRepository;
import com.demo.service.AssessmentService;
import com.demo.service.utils.MappingHelper;
import com.demo.web.dto.request.AssessmentReq;
import com.demo.web.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class AssessmentServiceImpl implements AssessmentService {

    private final AssessmentRepository assessmentRepository;
    private final ProductBillRepository productBillRepository;
    private final AccountRepository accountRepository;
    private final ImageRepository imageRepository;
    private final MappingHelper mappingHelper;

    @Override
    public void createAssessment(AssessmentReq assessmentReq) {
        var assessment = mappingHelper.map(assessmentReq, Assessment.class);
        var productBill = productBillRepository.findById(assessmentReq.getProductBillId())
                .orElseThrow(() -> new EntityNotFoundException(ProductBill.class.getName(),
                        assessmentReq.getProductBillId().toString()));
        assessment.setProductBill(productBill);
        assessment.setAccount(getCurrentAccount());
        assessmentRepository.save(assessment);

        //TODO: Update image of assessment

        for (String imageId : assessmentReq.getImageIds()) {
            var image = imageRepository.findById(UUID.fromString(imageId))
                    .orElseThrow(() -> new EntityNotFoundException(Image.class.getName(), imageId));
            image.setAssessment(assessment);
            imageRepository.save(image);
        }
    }

    @Override
    public Boolean checkExistAssessment(Integer productBillId) {
        return assessmentRepository.existsByAccountAndProductBill_Id(
                getCurrentAccount(), productBillId
        );
    }

    private Account getCurrentAccount() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return accountRepository.findOneWithAuthoritiesByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException(Account.class.getName(), username));
    }
}
