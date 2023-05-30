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
import com.demo.web.dto.AccountDto;
import com.demo.web.dto.AssessmentDto;
import com.demo.web.dto.ProductDetailDto;
import com.demo.web.dto.ProfileDto;
import com.demo.web.dto.request.AssessmentReq;
import com.demo.web.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

    @Override
    public List<AssessmentDto> getAssessmentOfProduct(Integer productId) {
        return assessmentRepository.findByProductBill_ProductDetail_Product_Id(productId)
                .stream().map(e -> {
                    var res = mappingHelper.map(e, AssessmentDto.class);

                    var profileDto = mappingHelper.map(e.getAccount().getProfile(), ProfileDto.class);
                    profileDto.setAccountDto(mappingHelper.map(e.getAccount(), AccountDto.class));
                    res.setProfileDto(profileDto);

                    var productDetailDto = mappingHelper.map(e.getProductBill().getProductDetail(), ProductDetailDto.class);
                    res.setProductDetailDto(productDetailDto);

                    return res;
                }).collect(Collectors.toList());
    }

    private Account getCurrentAccount() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return accountRepository.findOneWithAuthoritiesByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException(Account.class.getName(), username));
    }
}
