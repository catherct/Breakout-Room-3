package com.company.gamestore.repository;

import com.company.gamestore.model.Console;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ConsoleControllerRepositoryTest {
    @Autowired
    ConsoleControllerRepository consoleControllerRepository;
    @Autowired
    GameControllerRepository gameControllerRepository;
    @Autowired
    TshirtRepository tshirtRepository;
    @Autowired
    InvoiceRepository invoiceRepository;
    @Autowired
    ProcessingFeesRepository processingFeesRepository;
    @Autowired
    SalesTaxRateRepository salesTaxRateRepository;
    @Before
    public void setUp() throws Exception {

        consoleControllerRepository.deleteAll();
        gameControllerRepository.deleteAll();
        tshirtRepository.deleteAll();
        invoiceRepository.deleteAll();
        processingFeesRepository.deleteAll();
        salesTaxRateRepository.deleteAll();
    }

    @Test
    public void addGetDeleteConsole() {

        Console console = new Console();

        console.setModel("Xbox-360");
        console.setManufacturer("Microsoft");
        console.setMemory_amount("512MB");
        console.setProcessor("Xenon");
        console.setPrice(new BigDecimal("406.99"));
        console.setQuantity(20);


        console = consoleControllerRepository.save(console);

        Optional<Console> console1 = consoleControllerRepository.findById(console.getConsole_id());

        assertEquals(console1.get(), console);

        consoleControllerRepository.deleteById(console.getConsole_id());

        console1 = consoleControllerRepository.findById(console.getConsole_id());

        assertFalse(console1.isPresent());

    }

    @Test
    public void getAllConsoles() {


        Console console = new Console();

        console.setModel("Xbox-360");
        console.setManufacturer("Microsoft");
        console.setMemory_amount("512MB");
        console.setProcessor("Xenon");
        console.setPrice(new BigDecimal("406.99"));
        console.setQuantity(20);

        console = consoleControllerRepository.save(console);

        Console console2 = new Console();

        console2.setModel("Play Station");
        console2.setManufacturer("Sony");
        console2.setMemory_amount("1GB");
        console2.setProcessor("Intel");
        console2.setPrice(new BigDecimal("600.00"));
        console2.setQuantity(15);

        console2 = consoleControllerRepository.save(console2);

        List<Console> aList = consoleControllerRepository.findAll();

        assertEquals(aList.size(), 2);

    }

    @Test
    public void updateConsole() {

        Console console = new Console();

        console.setModel("Xbox-360");
        console.setManufacturer("Microsoft");
        console.setMemory_amount("512MB");
        console.setProcessor("Xenon");
        console.setPrice(new BigDecimal("406.99"));
        console.setQuantity(20);


        console = consoleControllerRepository.save(console);

        console.setMemory_amount("2GB");
        console.setProcessor("Intel");
        console.setPrice(new BigDecimal("478.59"));
        console.setQuantity(10);

        consoleControllerRepository.save(console);

        Optional<Console> console1 = consoleControllerRepository.findById(console.getConsole_id());
        assertEquals(console1.get(), console);

    }

    @Test
    public void findConsoleByManufacturer() {

        Console console = new Console();

        console.setModel("Xbox-360");
        console.setManufacturer("Microsoft");
        console.setMemory_amount("512MB");
        console.setProcessor("Xenon");
        console.setPrice(new BigDecimal("406.99"));
        console.setQuantity(20);

        console = consoleControllerRepository.save(console);

        List<Console> console1 = consoleControllerRepository.findConsoleByManufacturer("Microsoft");
        assertFalse(console1.isEmpty());

    }
}