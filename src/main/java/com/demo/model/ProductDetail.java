package com.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "productdetail")
@Data
public class ProductDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "price")
    private float price;
    @Column(name = "count_in_stock")
    private int countInStock;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "discount_id")
    private Discount discount;
    @ManyToOne
    @JoinColumn(name = "color_id")
    private Color color;
    @ManyToOne
    @JoinColumn(name = "size_id")
    private Size size;

    @OneToMany(mappedBy = "productDetail", fetch = FetchType.LAZY)
    private Set<ProductBillOrder> productBillOrders;
}
