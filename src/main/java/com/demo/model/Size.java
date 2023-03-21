package com.demo.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "sizes")
@Data
public class Size {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "name")
    private String name;
}
