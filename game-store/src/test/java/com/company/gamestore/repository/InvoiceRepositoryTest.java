package com.company.gamestore.repository;

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

    @Before
    public void setUp() throws Exception {
        invoiceRepository.deleteAll();
    }

    @Test
    public void addGetDeleteInvoice() {
        Invoice invoice = new Invoice();
        invoice.setName("Jose Salgado");
        invoice.setStreet("1 Irvine Ln.");
        invoice.setCity("Irvine");
        invoice.setState("CA");
        invoice.setZipcode("92617");
        invoice.setItemType("Console");
        invoice.setItemId(1);
        invoice.setQuantity(1);
        invoice.setSubtotal(new BigDecimal("10.00"));
        invoice.setTax(new BigDecimal("0.80"));
        invoice.setProcessingFee(new BigDecimal("1.25"));
        invoice.setTotal(new BigDecimal("15.00"));

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
        Invoice invoice = new Invoice();
        invoice.setName("Jose Salgado");
        invoice.setStreet("1 Irvine Ln.");
        invoice.setCity("Irvine");
        invoice.setState("CA");
        invoice.setZipcode("92617");
        invoice.setItemType("Console");
        invoice.setItemId(1);
        invoice.setQuantity(1);
        invoice.setSubtotal(new BigDecimal("10.00"));
        invoice.setTax(new BigDecimal("0.80"));
        invoice.setProcessingFee(new BigDecimal("1.25"));
        invoice.setTotal(new BigDecimal("15.00"));

        // Add
        invoice = invoiceRepository.save(invoice);

        // Update
        invoice.setName("Jose Salgado Jr.");
        invoice.setQuantity(2);
        invoice.setZipcode("92612");

        invoiceRepository.save(invoice);

        // Compare
        Optional<Invoice> invoice1 = invoiceRepository.findById(invoice.getId());
        assertEquals(invoice1.get(), invoice);
    }

    @Test
    public void getInvoiceByName() {
        Invoice invoice = new Invoice();
        invoice.setName("Jose Salgado");
        invoice.setStreet("1 Irvine Ln.");
        invoice.setCity("Irvine");
        invoice.setState("CA");
        invoice.setZipcode("92617");
        invoice.setItemType("Console");
        invoice.setItemId(1);
        invoice.setQuantity(1);
        invoice.setSubtotal(new BigDecimal("10.00"));
        invoice.setTax(new BigDecimal(".8"));
        invoice.setProcessingFee(new BigDecimal("1.25"));
        invoice.setTotal(new BigDecimal("15.00"));

        // Add 1
        invoice = invoiceRepository.save(invoice);

        invoice = new Invoice();
        invoice.setName("Joe Doe");
        invoice.setStreet("4 Oak St.");
        invoice.setCity("Bakersfield");
        invoice.setState("CA");
        invoice.setZipcode("93314");
        invoice.setItemType("Game");
        invoice.setItemId(32);
        invoice.setQuantity(4);
        invoice.setSubtotal(new BigDecimal("11.00"));
        invoice.setTax(new BigDecimal(".7"));
        invoice.setProcessingFee(new BigDecimal("1.30"));
        invoice.setTotal(new BigDecimal("10.00"));

        // Add 2
        invoice = invoiceRepository.save(invoice);

        invoice = new Invoice();
        invoice.setName("Jose Salgado");
        invoice.setStreet("4 Oak St.");
        invoice.setCity("Bakersfield");
        invoice.setState("CA");
        invoice.setZipcode("93314");
        invoice.setItemType("Game");
        invoice.setItemId(30);
        invoice.setQuantity(2);
        invoice.setSubtotal(new BigDecimal("15.00"));
        invoice.setTax(new BigDecimal(".7"));
        invoice.setProcessingFee(new BigDecimal("1.30"));
        invoice.setTotal(new BigDecimal("10.00"));

        // Add 3
        invoice = invoiceRepository.save(invoice);

        // Get
        List<Invoice> invoiceList = invoiceRepository.findByName("Jose Salgado");
        assertEquals(2, invoiceList.size());
    }

    @Test
    public void getAllInvoices() {
        Invoice invoice = new Invoice();
        invoice.setName("Jose Salgado");
        invoice.setStreet("1 Irvine Ln.");
        invoice.setCity("Irvine");
        invoice.setState("CA");
        invoice.setZipcode("92617");
        invoice.setItemType("Console");
        invoice.setItemId(1);
        invoice.setQuantity(1);
        invoice.setSubtotal(new BigDecimal("10.00"));
        invoice.setTax(new BigDecimal(".8"));
        invoice.setProcessingFee(new BigDecimal("1.25"));
        invoice.setTotal(new BigDecimal("15.00"));

        // Add 1
        invoice = invoiceRepository.save(invoice);

        invoice = new Invoice();
        invoice.setName("Joe Doe");
        invoice.setStreet("4 Oak St.");
        invoice.setCity("Bakersfield");
        invoice.setState("CA");
        invoice.setZipcode("93314");
        invoice.setItemType("Game");
        invoice.setItemId(32);
        invoice.setQuantity(4);
        invoice.setSubtotal(new BigDecimal("11.00"));
        invoice.setTax(new BigDecimal("0.70"));
        invoice.setProcessingFee(new BigDecimal("1.30"));
        invoice.setTotal(new BigDecimal("10.00"));

        // Add 2
        invoice = invoiceRepository.save(invoice);

        invoice = new Invoice();
        invoice.setName("Jose Salgado");
        invoice.setStreet("4 Oak St.");
        invoice.setCity("Bakersfield");
        invoice.setState("CA");
        invoice.setZipcode("93314");
        invoice.setItemType("Game");
        invoice.setItemId(30);
        invoice.setQuantity(2);
        invoice.setSubtotal(new BigDecimal("15.00"));
        invoice.setTax(new BigDecimal("0.70"));
        invoice.setProcessingFee(new BigDecimal("1.30"));
        invoice.setTotal(new BigDecimal("10.00"));

        // Add 3
        invoice = invoiceRepository.save(invoice);

        // Get
        List<Invoice> invoiceList = invoiceRepository.findAll();
        assertEquals(3, invoiceList.size());
    }
}