package com.demo.model;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "stores")
@Data
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "content")
    @Type(type = "text")
    private String content;
    @Column(name = "address")
    @Type(type = "text")
    private String address;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "working_hours")
    private Instant workingHours;
}
