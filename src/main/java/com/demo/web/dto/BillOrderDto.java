package com.demo.web.dto;

import com.demo.model.Account;
import com.demo.model.ProductBillOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class BillOrderDto {
    private Integer id;
    private String status;
    private Date paymentTime;
    private float totalAmount;
    private Account account;
    private Set<ProductBillOrder> productBillOrders;
}
