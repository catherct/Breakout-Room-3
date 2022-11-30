package com.company.gamestore.repository;

import com.company.gamestore.model.ProcessingFee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.Optional;

public interface ProcessingFeesRepository extends JpaRepository <ProcessingFee, String> {
    Optional<BigDecimal> findProcessingFeesByProduct (String product);
}
