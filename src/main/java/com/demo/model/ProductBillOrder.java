package com.demo.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "productbillorder")
@Data
public class ProductBillOrder {
    @EmbeddedId
    private ProductBillOrderKey id;
    @ManyToOne
    @MapsId("billOrderId")
    @JoinColumn(name = "bill_order_id")
    private BillOrder billOrder;
    @ManyToOne
    @MapsId("productDetailId")
    @JoinColumn(name = "product_detail_id")
    private ProductDetail productDetail;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "price")
    private float price;
}