package com.demo.web.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AddressReq {
    @NotBlank
    private String fullName;
    @NotBlank
    private String phoneNumber;
    @NotBlank
    private String province;
    @NotBlank
    private String district;
    @NotBlank
    private String ward;
    private String content;
    private Boolean defaultAddress;
}
