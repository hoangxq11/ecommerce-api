package com.demo.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "productbill")
@Data
public class ProductBill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "bill_id")
    private Bill bill;
    @ManyToOne
    @JoinColumn(name = "product_detail_id")
    private ProductDetail productDetail;
}
