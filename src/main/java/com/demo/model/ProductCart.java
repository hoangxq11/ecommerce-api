package com.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "productcart")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductCart {
    @EmbeddedId
    private ProductCartKey id;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("cartId")
    @JoinColumn(name = "cart_id")
    private Cart cart;
    @ManyToOne
    @MapsId("productDetailId")
    @JoinColumn(name = "product_detail_id")
    private ProductDetail productDetail;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "checked", nullable = false)
    private Boolean checked;
}
