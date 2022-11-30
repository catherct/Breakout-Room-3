package com.company.gamestore.controller;

import com.company.gamestore.model.Invoice;
import com.company.gamestore.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class InvoiceController {
    @Autowired
    InvoiceRepository repo;

    // Create a new invoice.
    @PostMapping("/invoice")
    @ResponseStatus(HttpStatus.CREATED)
    public Invoice addInvoice(@RequestBody Invoice invoice) {
        return repo.save(invoice);
    }

    // Find invoice by id.
    @GetMapping("/invoice/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Invoice getInvoiceById(@PathVariable int id) {
        Optional<Invoice> returnVal = repo.findById(id);
        return returnVal.orElse(null);
    }

    // Find invoices by customer name.
    @GetMapping("/invoice/name/{name}")
    @ResponseStatus(HttpStatus.OK)
    public List<Invoice> getInvoiceByName(@PathVariable String name) {
        return repo.findByName(name);
    }

    // Get all invoices.
    @GetMapping("/invoice")
    @ResponseStatus(HttpStatus.OK)
    public List<Invoice> getInvoices() {
        return repo.findAll();
    }

    // Update an existing invoice.
    @PutMapping("/invoice")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateInvoice(@RequestBody Invoice invoice) {
        repo.save(invoice);
    }

    // Delete an existing invoice.
    @DeleteMapping("/invoice/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInvoice(@PathVariable int id) {
        repo.deleteById(id);
    }
}
