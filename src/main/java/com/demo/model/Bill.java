package com.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "bills")
@Data
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "status")
    private String status;
    @Column(name = "payment_method")
    private String paymentMethod;
    @Column(name = "description_pay")
    private String descriptionPay;
    @Column(name = "total_amount")
    private Float totalAmount;
    @Column(name = "payment_time")
    private Date paymentTime;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;
    @ManyToOne
    @JoinColumn(name = "shipping_service_id")
    private ShippingService shippingService;
    @ManyToOne
    @JoinColumn(name = "order_type_id")
    private OrderType orderType;
    @OneToMany(mappedBy = "bill")
    List<ProductBill> productBills;
}
