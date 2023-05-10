package com.demo.model;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "shippingservice")
@Data
public class ShippingService {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "cost")
    private Float cost;
    @Column(name = "time")
    private Integer time;
    @Column(name = "description")
    @Type(type = "text")
    private String description;
}
