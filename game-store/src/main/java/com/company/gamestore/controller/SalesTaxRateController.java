package com.company.gamestore.controller;

import com.company.gamestore.model.Console;
import com.company.gamestore.repository.ConsoleControllerRepository;
import com.company.gamestore.repository.SalesTaxRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
public class SalesTaxRateController {


//    @Autowired
//    SalesTaxRateRepository repo;
//
//    //Get sales tax rate by state
//    @GetMapping("/sales_tax_rate/{state}")
//    @ResponseStatus(value = HttpStatus.OK)
//    public Optional<BigDecimal> getSalesTaxRateByState(@PathVariable String state) {
//
//        Optional<BigDecimal> returnVal = repo.findSalesTaxRateByState(state);
//        return repo.findSalesTaxRateByState(state);
//    }


}
