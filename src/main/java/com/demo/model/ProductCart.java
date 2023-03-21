package com.demo.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "productcart")
@Data
public class ProductCart {
    @EmbeddedId
    private ProductCartKey id;
    @ManyToOne
    @MapsId("cartId")
    @JoinColumn(name = "cart_id")
    private Cart cart;
    @ManyToOne
    @MapsId("productDetailId")
    @JoinColumn(name = "product_detail_id")
    private ProductDetail productDetail;
    @Column(name = "quantity")
    private int quantity;
}
