package com.company.gamestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.Optional;

public interface ProcessingFeesRepository extends JpaRepository {
    Optional<BigDecimal> findProcessingFeesByProduct (String product);
}
