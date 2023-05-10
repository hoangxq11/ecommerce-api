package com.demo.web.dto.request;

import com.demo.model.OrderType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillReq {
    private String paymentMethod;
    private String descriptionPay;
    private float totalAmount;
    private Integer addressId;
    private Integer shippingServiceId;
}
