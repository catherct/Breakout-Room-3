package com.company.gamestore.controller;

import com.company.gamestore.repository.ProcessingFeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
public class ProcessingFeeController {
    @Autowired
    ProcessingFeesRepository repo;

    //Get processing fee by product
    @GetMapping("/processing_fee/{product}")
    @ResponseStatus(value = HttpStatus.OK)
    public Optional<BigDecimal> getProcessingFeesByProduct (@PathVariable String product) {

        Optional<BigDecimal> returnVal = repo.findProcessingFeesByProductType (product);
        return repo.findProcessingFeesByProductType(product);
    }
}
