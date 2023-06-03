package com.demo.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupByStatusBillRes {
    private int pending;
    private int done;
    private int shipping;
    private int canceled;
}
