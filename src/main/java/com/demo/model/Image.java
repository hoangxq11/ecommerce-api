package com.demo.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "images")
@Data
public class Image {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "uuid", columnDefinition = "char(32)")
    @Type(type = "org.hibernate.type.UUIDCharType")
    private Long id;
    @Column(name = "url_image")
    @Type(type = "text")
    private String imageUrl;
    @Column(name = "title")
    private String title;
    @ManyToOne
    @JoinColumn(name = "assessment_id")
    private Assessment assessment;
    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
