package com.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "billorder")
@Data
public class BillOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "status")
    private String status;
    @Column(name = "payment_time")
    private Date paymentTime;
    @Column(name = "total_amount")
    private float totalAmount;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
}
