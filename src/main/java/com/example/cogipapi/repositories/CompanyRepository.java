package com.example.cogipapi.repositories;


import com.example.cogipapi.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    // Custom query to find companies by type (provider/client)
    List<Company> findByType(String type);

    // Custom query to find companies by VAT number
    Company findByVatNumber(String vatNumber);

    // Custom query to find companies by name
    List<Company> findByNameContainingIgnoreCase(String name);

}
