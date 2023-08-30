package com.example.cogipapi.models;

import jakarta.persistence.*;
import lombok.*;


import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "invoice")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Timestamp timestamp;

    @ManyToOne
    @JoinColumn(name = "invoice_company_id")
    private Company company;

    @ManyToOne
    @JoinColumn(name = "invoice_contact_id")
    private Contact contact;
}
