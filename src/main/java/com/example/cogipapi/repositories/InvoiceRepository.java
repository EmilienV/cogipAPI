package com.example.cogipapi.repositories;

import com.example.cogipapi.models.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    // Custom query to find invoices by company ID
    List<Invoice> findByCompanyId(Long companyId);

    // Custom query to find invoices within a date range
    List<Invoice> findByTimestampBetween(Timestamp startDate, Timestamp endDate);


}