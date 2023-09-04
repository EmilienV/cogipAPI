package com.example.cogipapi.services;

import com.example.cogipapi.models.Invoice;
import com.example.cogipapi.repositories.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;

    @Autowired
    public InvoiceService(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    public Invoice getInvoiceById(Long id) {
        return invoiceRepository.findById(id).orElse(null);
    }

    public List<Invoice> getInvoicesByCompanyId(Long companyId) {
        return invoiceRepository.findByCompanyId(companyId);
    }


    public List<Invoice> getInvoicesByDateRange(Timestamp startDate, Timestamp endDate) {
        Timestamp startTimestamp = new Timestamp(startDate.getTime());
        Timestamp endTimestamp = new Timestamp(endDate.getTime());
        return invoiceRepository.findByTimestampBetween(startTimestamp, endTimestamp);
    }

    public Invoice createInvoice(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    public Invoice updateInvoice(Long id, Invoice updatedInvoice) {
        Invoice existingInvoice = getInvoiceById(id);

        if (existingInvoice != null) {
            // Update properties of existingInvoice with updatedInvoice
            existingInvoice.setTimestamp(updatedInvoice.getTimestamp());
            existingInvoice.setCompany(updatedInvoice.getCompany());
            existingInvoice.setContact(updatedInvoice.getContact());

            // Save and return the updated invoice
            return invoiceRepository.save(existingInvoice);
        } else {
            return null; // Invoice with the given ID not found
        }
    }

    public boolean deleteInvoice(Long id) {
        Invoice existingInvoice = getInvoiceById(id);

        if (existingInvoice != null) {
            invoiceRepository.delete(existingInvoice);
            return true; // Deletion successful
        } else {
            return false; // Invoice with the given ID not found
        }
    }
}