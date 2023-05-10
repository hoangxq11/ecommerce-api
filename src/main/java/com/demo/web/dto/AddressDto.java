package com.demo.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {
    private Integer id;
    private String fullName;
    private String phoneNumber;
    private String province;
    private String district;
    private String ward;
    private String content;
    private Boolean defaultAddress;
}
