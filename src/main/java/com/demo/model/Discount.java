package com.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "discounts")
@Data
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "nam")
    private String name;
    @Column(name = "value")
    private int value;
    @Column(name = "end_date")
    private Instant endDate;
}
