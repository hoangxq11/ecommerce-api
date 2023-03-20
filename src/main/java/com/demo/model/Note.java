package com.demo.model;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "notes")
@Data
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "text")
    @Type(type = "text")
    private String description;
    @Column(name = "created_date")
    private Instant createdDate;
    @ManyToOne
    @JoinColumn(name = "bill_order_id")
    private BillOrder billOrder;
}
