package com.example.cogipapi.models;

import jakarta.persistence.*;
@Entity
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstname;
    private String lastname;
    private String phone;
    private String email;

    @ManyToOne
    @JoinColumn(name = "contact_company_id")
    private Company contactCompany;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Company getContactCompany() {
        return contactCompany;
    }

    public void setContactCompany(Company contactCompany) {
        this.contactCompany = contactCompany;
    }

    // Constructors, getters, and setters
}
