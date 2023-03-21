package com.demo.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ProductBillOrderKey implements Serializable {
    @Column(name = "bill_order_id")
    Integer billOrderId;
    @Column(name = "product_detail_id")
    Integer productDetailId;
}
