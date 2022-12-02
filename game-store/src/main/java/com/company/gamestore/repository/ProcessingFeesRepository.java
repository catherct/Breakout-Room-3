package com.company.gamestore.repository;

import com.company.gamestore.model.ProcessingFee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface ProcessingFeesRepository extends JpaRepository<ProcessingFee, Integer> {
    Optional<ProcessingFee> findProcessingFeesByProduct (String product);
}
