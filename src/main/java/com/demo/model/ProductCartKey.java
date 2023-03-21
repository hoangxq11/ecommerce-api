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
public class ProductCartKey implements Serializable {
    @Column(name = "cart_id")
    private Integer cartId;
    @Column(name = "product_detail_id")
    private Integer productDetailId;
}
