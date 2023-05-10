package com.demo.web.dto;

import com.demo.model.OrderType;
import com.demo.model.ShippingService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillDto {
    private Integer id;
    private String status;
    private String paymentMethod;
    private String descriptionPay;
    private Float totalAmount;
    private Date paymentTime;
    private AddressDto address;
    private ShippingService shippingService;
    private OrderType orderType;
    private ProfileDto profileDto;
    private List<ProductBillDto> productBills;
}
