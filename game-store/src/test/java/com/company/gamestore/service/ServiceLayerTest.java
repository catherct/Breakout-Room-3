package com.company.gamestore.service;

import com.company.gamestore.model.Invoice;
import com.company.gamestore.model.ProcessingFee;
import com.company.gamestore.repository.*;
import com.company.gamestore.viewmodel.InvoiceViewModel;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

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

    // Helper method
    private void setUpInvoiceRepositoryMock() {
        invoiceRepository = mock(InvoiceRepository.class);
        Invoice invoice = new Invoice();
        invoice.setId(1);
        invoice.setName("Jose Salgado");
        invoice.setStreet("1 Irvine Ln.");
        invoice.setCity("Irvine");
        invoice.setState("CA");
        invoice.setZipcode("92617");
        invoice.setItemType("Console");
        invoice.setItemId(1);
        invoice.setQuantity(1);

        Invoice invoice2 = new Invoice();
        invoice.setName("Joe Doe");
        invoice.setStreet("4 Oak St.");
        invoice.setCity("Bakersfield");
        invoice.setState("CA");
        invoice.setZipcode("93314");
        invoice.setItemType("Game");
        invoice.setItemId(32);
        invoice.setQuantity(4);

        List<Invoice> iList = new ArrayList<>();
        iList.add(invoice);

        doReturn(invoice).when(invoiceRepository).save(invoice2);
        doReturn(Optional.of(invoice)).when(invoiceRepository).findById(1);
        doReturn(iList).when(invoiceRepository).findAll();
    }

    /*private void setUpProcessingFeesRepositoryMock() {
        processingFeesRepository = mock(ProcessingFeesRepository.class);
        ProcessingFee processingFee = new ProcessingFee();
        processingFee.setProduct_type("Game");
        processingFee.setFee(new BigDecimal(1.49));

        ProcessingFee processingFee1 = new ProcessingFee();
        processingFee1.setProduct_type("Console");
        processingFee1.setFee(new BigDecimal(14.99));

        List<ProcessingFee> pfList = new ArrayList<>();
        pfList.add(processingFee);

        doReturn(processingFee).when(processingFeesRepository).save(processingFee1);
        doReturn(Optional.of(processingFee)).when(processingFeesRepository).findProcessingFeesByProduct("Game");
        doReturn(pfList).when(invoiceRepository).findAll();
    }*/

    private void setUpSalesTaxRateRepositoryMock() {
        salesTaxRateRepository = mock(SalesTaxRateRepository.class);
    }

    /*@Test
    public void shouldSaveInvoice() {
        // Arrange
        InvoiceViewModel expectedResult = new InvoiceViewModel();
        expectedResult.setId(2);
        expectedResult.setName("Susan Lady");
        expectedResult.setStreet("1 Pine Dr.");
        expectedResult.setCity("Shafter");
        expectedResult.setState("CA");
        expectedResult.setZipcode("93262");
        expectedResult.setItemType("Console");
        expectedResult.setItemId(1);
        expectedResult.setQuantity(1);

        InvoiceViewModel invoice = new InvoiceViewModel();
        expectedResult.setName("Susan Lady");
        expectedResult.setStreet("1 Pine Dr.");
        expectedResult.setCity("Shafter");
        expectedResult.setState("CA");
        expectedResult.setZipcode("93262");
        expectedResult.setItemType("Console");
        expectedResult.setItemId(1);
        expectedResult.setQuantity(1);

        // ACT
        invoice = service.saveInvoice(invoice);
        assertEquals(expectedResult, invoice);

    }*/
}