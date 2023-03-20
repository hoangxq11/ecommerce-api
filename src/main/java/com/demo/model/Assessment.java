package com.demo.model;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "sssessments")
@Data
public class Assessment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "comment")
    @Type(type = "text")
    private String comment;
    @Column(name = "star")
    private Integer star;
    @ManyToOne
    @JoinColumn(name = "sccount_id")
    private Account account;
    // TODO: Field product bill id
}
