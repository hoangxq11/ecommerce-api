package com.demo.model;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Data
@Entity
@Table(name = "profiles")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "address")
    @Type(type = "text")
    private String address;
//    @ManyToOne
//    @JoinColumn(name = "image_id")
//    private Image image;
    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;
}
