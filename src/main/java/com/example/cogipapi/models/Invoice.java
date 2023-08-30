package com.example.cogipapi.models;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date timestamp;

    @ManyToOne
    @JoinColumn(name = "invoice_company_id")
    private Company invoiceCompany;

    @ManyToOne
    @JoinColumn(name = "invoice_contact_id")
    private Contact invoiceContact;

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Company getInvoiceCompany() {
        return invoiceCompany;
    }

    public void setInvoiceCompany(Company invoiceCompany) {
        this.invoiceCompany = invoiceCompany;
    }

    public Contact getInvoiceContact() {
        return invoiceContact;
    }

    public void setInvoiceContact(Contact invoiceContact) {
        this.invoiceContact = invoiceContact;
    }

    // Constructors, getters, and setters
}