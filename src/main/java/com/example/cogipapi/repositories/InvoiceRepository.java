package com.example.cogipapi.repositories;

import com.example.cogipapi.models.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    // Custom query to find invoices by company ID
    List<Invoice> findByCompanyId(Long companyId);

    // Custom query to find invoices by invoice type (e.g., paid, unpaid)
    List<Invoice> findByType(String type);

    // Custom query to find invoices within a date range
    List<Invoice> findByInvoiceDateBetween(Date startDate, Date endDate);

}