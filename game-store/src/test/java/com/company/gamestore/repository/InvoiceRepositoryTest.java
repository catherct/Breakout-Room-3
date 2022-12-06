package com.company.gamestore.repository;

import com.company.gamestore.model.Console;
import com.company.gamestore.model.Game;
import com.company.gamestore.model.Invoice;
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
public class InvoiceRepositoryTest {
    @Autowired
    InvoiceRepository invoiceRepository;
    @Autowired
    ConsoleControllerRepository consoleRepository;
    @Autowired
    GameControllerRepository gameRepository;

    @Before
    public void setUp() throws Exception {
        invoiceRepository.deleteAll();
    }

    @Test
    public void addGetDeleteInvoice() {
        Console console = new Console();
        console.setModel("Xbox-360");
        console.setManufacturer("Microsoft");
        console.setMemory_amount("512MB");
        console.setProcessor("Xenon");
        console.setPrice(new BigDecimal("406.99"));
        console.setQuantity(20);

        console = consoleRepository.save(console);

        Invoice invoice = new Invoice();
        invoice.setName("Jose Salgado");
        invoice.setStreet("1 Irvine Ln.");
        invoice.setCity("Irvine");
        invoice.setState("CA");
        invoice.setZipcode("92617");
        invoice.setItemType("Console");
        invoice.setItemId(console.getConsole_id());
        invoice.setQuantity(1);
        invoice.setUnitPrice(console.getPrice());
        invoice.setSubtotal(console.getPrice());
        invoice.setTax(new BigDecimal("24.41"));
        invoice.setProcessingFee(new BigDecimal("14.99"));
        invoice.setTotal(new BigDecimal("446.39"));

        // Add
        invoice = invoiceRepository.save(invoice);

        // Get
        Optional<Invoice> invoice1 = invoiceRepository.findById(invoice.getId());
        assertEquals(invoice1.get(), invoice);

        // Delete
        invoiceRepository.deleteById(invoice.getId());
        invoice1 = invoiceRepository.findById(invoice.getId());
        assertFalse(invoice1.isPresent());
    }

    @Test
    public void updateInvoice() {
        Console console = new Console();
        console.setModel("Xbox-360");
        console.setManufacturer("Microsoft");
        console.setMemory_amount("512MB");
        console.setProcessor("Xenon");
        console.setPrice(new BigDecimal("406.99"));
        console.setQuantity(20);

        console = consoleRepository.save(console);

        Invoice invoice = new Invoice();
        invoice.setName("Jose Salgado");
        invoice.setStreet("1 Irvine Ln.");
        invoice.setCity("Irvine");
        invoice.setState("CA");
        invoice.setZipcode("92617");
        invoice.setItemType("Console");
        invoice.setItemId(console.getConsole_id());
        invoice.setQuantity(1);
        invoice.setUnitPrice(console.getPrice());
        invoice.setSubtotal(console.getPrice());
        invoice.setTax(new BigDecimal("24.41"));
        invoice.setProcessingFee(new BigDecimal("14.99"));
        invoice.setTotal(new BigDecimal("446.39"));

        // Add
        invoice = invoiceRepository.save(invoice);

        // Update
        invoice.setName("Jose Salgado Jr.");
        invoice.setZipcode("92612");

        invoiceRepository.save(invoice);

        // Compare
        Optional<Invoice> invoice1 = invoiceRepository.findById(invoice.getId());
        assertEquals(invoice1.get(), invoice);
    }

    @Test
    public void getInvoiceByName() {
        Game game = new Game();
        game.setTitle("Halo");
        game.setEsrbRating("M");
        game.setDescription("FPS");
        game.setPrice(new BigDecimal("60.00"));
        game.setStudio("Bungie");
        game.setQuantity(20);

        game = gameRepository.save(game);

        Console console = new Console();
        console.setModel("Xbox-360");
        console.setManufacturer("Microsoft");
        console.setMemory_amount("512MB");
        console.setProcessor("Xenon");
        console.setPrice(new BigDecimal("406.99"));
        console.setQuantity(20);

        console = consoleRepository.save(console);

        Invoice invoice = new Invoice();
        invoice.setName("Jose Salgado");
        invoice.setStreet("1 Irvine Ln.");
        invoice.setCity("Irvine");
        invoice.setState("CA");
        invoice.setZipcode("92617");
        invoice.setItemType("Game");
        invoice.setItemId(game.getId());
        invoice.setQuantity(1);
        invoice.setUnitPrice(new BigDecimal("60.00"));
        invoice.setSubtotal(new BigDecimal("60.00"));
        invoice.setTax(new BigDecimal("3.60"));
        invoice.setProcessingFee(new BigDecimal("1.49"));
        invoice.setTotal(new BigDecimal("65.09"));

        // Add 1
        invoice = invoiceRepository.save(invoice);

        invoice = new Invoice();
        invoice.setName("Joe Doe");
        invoice.setStreet("4 Oak St.");
        invoice.setCity("Bakersfield");
        invoice.setState("CA");
        invoice.setZipcode("93314");
        invoice.setItemType("Console");
        invoice.setItemId(console.getConsole_id());
        invoice.setQuantity(1);
        invoice.setUnitPrice(console.getPrice());
        invoice.setSubtotal(console.getPrice());
        invoice.setTax(new BigDecimal("24.41"));
        invoice.setProcessingFee(new BigDecimal("14.99"));
        invoice.setTotal(new BigDecimal("446.39"));

        // Add 2
        invoice = invoiceRepository.save(invoice);

        invoice = new Invoice();
        invoice.setName("Jose Salgado");
        invoice.setStreet("4 4th St.");
        invoice.setCity("Bakersfield");
        invoice.setState("CA");
        invoice.setZipcode("93314");
        invoice.setItemType("Console");
        invoice.setItemId(console.getConsole_id());
        invoice.setQuantity(1);
        invoice.setUnitPrice(console.getPrice());
        invoice.setSubtotal(console.getPrice());
        invoice.setTax(new BigDecimal("24.41"));
        invoice.setProcessingFee(new BigDecimal("14.99"));
        invoice.setTotal(new BigDecimal("446.39"));

        // Add 3
        invoice = invoiceRepository.save(invoice);

        // Get
        List<Invoice> invoiceList = invoiceRepository.findByName("Jose Salgado");
        assertEquals(2, invoiceList.size());
    }

    @Test
    public void getAllInvoices() {
        Game game = new Game();
        game.setTitle("Halo");
        game.setEsrbRating("M");
        game.setDescription("FPS");
        game.setPrice(new BigDecimal("60.00"));
        game.setStudio("Bungie");
        game.setQuantity(20);

        game = gameRepository.save(game);

        Console console = new Console();
        console.setModel("Xbox-360");
        console.setManufacturer("Microsoft");
        console.setMemory_amount("512MB");
        console.setProcessor("Xenon");
        console.setPrice(new BigDecimal("406.99"));
        console.setQuantity(20);

        console = consoleRepository.save(console);

        Invoice invoice = new Invoice();
        invoice.setName("Jose Salgado");
        invoice.setStreet("1 Irvine Ln.");
        invoice.setCity("Irvine");
        invoice.setState("CA");
        invoice.setZipcode("92617");
        invoice.setItemType("Game");
        invoice.setItemId(game.getId());
        invoice.setQuantity(1);
        invoice.setUnitPrice(new BigDecimal("60.00"));
        invoice.setSubtotal(new BigDecimal("60.00"));
        invoice.setTax(new BigDecimal("3.60"));
        invoice.setProcessingFee(new BigDecimal("1.49"));
        invoice.setTotal(new BigDecimal("65.09"));

        // Add 1
        invoice = invoiceRepository.save(invoice);

        invoice = new Invoice();
        invoice.setName("Joe Doe");
        invoice.setStreet("4 Oak St.");
        invoice.setCity("Bakersfield");
        invoice.setState("CA");
        invoice.setZipcode("93314");
        invoice.setItemType("Console");
        invoice.setItemId(console.getConsole_id());
        invoice.setQuantity(1);
        invoice.setUnitPrice(console.getPrice());
        invoice.setSubtotal(console.getPrice());
        invoice.setTax(new BigDecimal("24.41"));
        invoice.setProcessingFee(new BigDecimal("14.99"));
        invoice.setTotal(new BigDecimal("446.39"));

        // Add 2
        invoice = invoiceRepository.save(invoice);

        invoice = new Invoice();
        invoice.setName("Jose Salgado");
        invoice.setStreet("4 4th St.");
        invoice.setCity("Bakersfield");
        invoice.setState("CA");
        invoice.setZipcode("93314");
        invoice.setItemType("Console");
        invoice.setItemId(console.getConsole_id());
        invoice.setQuantity(1);
        invoice.setUnitPrice(console.getPrice());
        invoice.setSubtotal(console.getPrice());
        invoice.setTax(new BigDecimal("24.41"));
        invoice.setProcessingFee(new BigDecimal("14.99"));
        invoice.setTotal(new BigDecimal("446.39"));

        // Add 3
        invoice = invoiceRepository.save(invoice);

        // Get
        List<Invoice> invoiceList = invoiceRepository.findAll();
        assertEquals(3, invoiceList.size());
    }
}