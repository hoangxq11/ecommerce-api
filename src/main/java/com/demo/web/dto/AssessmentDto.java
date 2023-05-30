package com.demo.web.dto;

import com.demo.model.Image;
import lombok.Data;

import java.util.List;

@Data
public class AssessmentDto {
    private Integer id;
    private String comment;
    private Integer star;
    private ProfileDto profileDto;
    private ProductDetailDto productDetailDto;
    private List<Image> images;
}
