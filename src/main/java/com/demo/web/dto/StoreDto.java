package com.demo.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class StoreDto {
    private Integer id;
    private String name;
    private String content;
    private String address;
    private String phoneNumber;
    private Instant workingHours;
}
