package com.demo.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SupplierDto {

    private Integer id;
    private String name;
    private String phoneNumber;
    private String address;
    private String note;
}
