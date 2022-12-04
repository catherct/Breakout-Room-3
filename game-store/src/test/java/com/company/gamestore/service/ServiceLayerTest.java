package com.company.gamestore.service;

import com.company.gamestore.model.Console;
import com.company.gamestore.repository.*;
import com.company.gamestore.viewmodel.ConsoleViewModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
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
        setUpConsoleRepositoryMock();
        //we will each att our set-up mock to this function
        service = new ServiceLayer(consoleControllerRepository,gameControllerRepository,invoiceRepository,
                processingFeesRepository,salesTaxRateRepository,tshirtRepository);
    }

    // Helper methods
    private void setUpConsoleRepositoryMock() {
        consoleControllerRepository = mock(ConsoleControllerRepository.class);
        Console console = new Console();
        console.setConsole_id(1);
        console.setModel("Xbox-360");
        console.setManufacturer("Microsoft");
        console.setMemory_amount("512MB");
        console.setProcessor("Xenon");
        console.setPrice(new BigDecimal("406.99"));
        console.setQuantity(20);


        Console console2 = new Console();

        console2.setModel("Xbox-360");
        console2.setManufacturer("Microsoft");
        console2.setMemory_amount("512MB");
        console2.setProcessor("Xenon");
        console2.setPrice(new BigDecimal("406.99"));
        console2.setQuantity(20);

        List<Console> aList = new ArrayList<>();
        aList.add(console);

        List<Console> bList = new ArrayList<>();
        bList.add(console);

        doReturn(console).when(consoleControllerRepository).save(console2);
        doReturn(Optional.of(console)).when(consoleControllerRepository).findById(1);
        doReturn(bList).when(consoleControllerRepository).findAll();
        doReturn(bList).when(consoleControllerRepository).findConsoleByManufacturer("Microsoft");
    }

    @Test
    public void shouldSaveConsole() throws Exception {

        //Test is failing

        List<ConsoleViewModel> sampleConsole = new ArrayList<>();

        ConsoleViewModel newConsole = new ConsoleViewModel();
        newConsole.setId(1);
        newConsole.setModel("Xbox-360");
        newConsole.setManufacturer("Microsoft");
        newConsole.setMemory_amount("512MB");
        newConsole.setProcessor("Xenon");
        newConsole.setPrice(new BigDecimal("406.99"));
        newConsole.setQuantity(20);
        sampleConsole.add(newConsole);

        // act
        newConsole = service.saveConsole(newConsole);
        assertTrue(sampleConsole.isEmpty());

    }

    @Test
    public void shouldGetConsoleById() {

        // arrange
        ConsoleViewModel toCompare = new ConsoleViewModel();
        toCompare.setId(1);
        toCompare.setModel("Xbox-360");
        toCompare.setManufacturer("Microsoft");
        toCompare.setMemory_amount("512MB");
        toCompare.setProcessor("Xenon");
        toCompare.setPrice(new BigDecimal("406.99"));
        toCompare.setQuantity(20);

        // act
        ConsoleViewModel consoleViewModel = service.findConsole(1);
        assertEquals(consoleViewModel, toCompare);

    }

    @Test
    public void shouldGetAllConsoles() {

        List<ConsoleViewModel> consolelist=new ArrayList<>();
        // arrange
        ConsoleViewModel toCompare1 = new ConsoleViewModel();
        toCompare1.setId(1);
        toCompare1.setModel("Xbox-360");
        toCompare1.setManufacturer("Microsoft");
        toCompare1.setMemory_amount("512MB");
        toCompare1.setProcessor("Xenon");
        toCompare1.setPrice(new BigDecimal("406.99"));
        toCompare1.setQuantity(20);

        consolelist.add(toCompare1);


        // act
        List<ConsoleViewModel> consoleViewModel = service.findAllConsoles();
        assertEquals(consoleViewModel.size(),consolelist.size());

    }

    @Test
    public void shouldGetConsolesByManufacturer() {

        // arrange
        ConsoleViewModel toCompare = new ConsoleViewModel();
        toCompare.setId(1);
        toCompare.setModel("Xbox-360");
        toCompare.setManufacturer("Microsoft");
        toCompare.setMemory_amount("512MB");
        toCompare.setProcessor("Xenon");
        toCompare.setPrice(new BigDecimal("406.99"));
        toCompare.setQuantity(20);


        // act

        List<ConsoleViewModel> aList = service.findConsoleByManufacturer("Microsoft");

        Assert.assertEquals(aList.size(),1);

    }
}