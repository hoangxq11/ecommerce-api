package com.demo.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "categories")
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Category categoryParent;
    @Column(name = "name")
    private String name;
    @OneToOne
    @JoinColumn(name = "image_id")
    private Image image;
    @Column(name = "isDeleted")
    private Boolean isDeleted;
}
