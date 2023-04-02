package com.demo.web.dto;

import lombok.Data;

@Data
public class AddressDto {
    private Integer id;
    private String fullName;
    private String phoneNumber;
    private String province;
    private String district;
    private String ward;
    private String content;
    private boolean defaultAddress;
}
