package com.demo.model;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "address")
@Data
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "content")
    @Type(type = "text")
    private String content;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
}
