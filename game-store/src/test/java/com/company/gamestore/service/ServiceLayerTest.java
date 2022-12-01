package com.company.gamestore.service;

import com.company.gamestore.repository.*;
import org.junit.Before;

import static org.junit.Assert.*;

public class ServiceLayerTest {

    ServiceLayer service;
    ConsoleControllerRepository consoleControllerRepository;
    GameControllerRepository gameControllerRepository;
    InvoiceRepository invoiceRepository;
    ProcessingFeesRepository processingFeesRepository;
    SalesTaxRateRepository salesTaxRateRepository;
    TshirtRepository tshirtRepository;

    @Before
    public void setUp() throws Exception{
        //we will each att our set up mock to this funciton
        service = new ServiceLayer(consoleControllerRepository,gameControllerRepository,invoiceRepository,
                processingFeesRepository,salesTaxRateRepository,tshirtRepository);
    }
}