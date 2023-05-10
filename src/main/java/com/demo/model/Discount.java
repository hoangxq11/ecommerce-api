package com.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;

@Entity
@Table(name = "discounts")
@Data
@NoArgsConstructor
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "nam")
    private String name;
    @Column(name = "value")
    private int value;
    @Column(name = "end_date")
    private Date endDate;

    public Discount(int value, Date endDate) {
        this.value = value;
        this.endDate = endDate;
    }
}
