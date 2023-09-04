package com.example.cogipapi.repositories;

import com.example.cogipapi.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    // Custom query to find contacts by company ID
    List<Contact> findByCompanyId(Long companyId);

    // Custom query to find contacts by first name and last name
    List<Contact> findByFirstNameAndLastName(String firstName, String lastName);

    // Custom query to find contacts by mobile number
    List<Contact> findByPhone(String phone);

    // Custom query to find contacts by email
    List<Contact> findByEmail(String email);

}