package com.demo.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KpiRes {
    private int revenueFirstSpin;
    private int revenueSecondSpin;
    private int revenueThirdSpin;
    private int revenuePredict;
    private int kpi;
}
