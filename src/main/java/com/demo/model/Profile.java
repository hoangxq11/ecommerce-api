package com.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
    @Column(name = "date_of_irth")
    private Date dateOfBirth;
    @Column(name = "gender")
    private String gender;
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
