package com.company.gamestore.repository;

import com.company.gamestore.model.SalesTaxRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SalesTaxRateRepository extends JpaRepository<SalesTaxRate, Integer> {
    Optional<SalesTaxRate> findSalesTaxRateByState (String state);
}
