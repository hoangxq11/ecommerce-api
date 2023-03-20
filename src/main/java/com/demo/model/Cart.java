package com.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "carts")
@Data
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "date_created")
    private Instant createdDate;
    @Column(name = "quantity")
    private int quantity;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
}
