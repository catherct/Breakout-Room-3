package com.company.gamestore.controller;

import com.company.gamestore.service.ServiceLayer;
import com.company.gamestore.viewmodel.InvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InvoiceController {
    @Autowired
    private ServiceLayer serviceLayer;

    // Create a new invoice.
//    @PostMapping("/invoice")
//    @ResponseStatus(HttpStatus.CREATED)
//    public InvoiceViewModel addInvoice(@RequestBody InvoiceViewModel invoice) {
//        return serviceLayer.saveInvoice(invoice);
//    }

    // Find invoice by id.
    @GetMapping("/invoice/{id}")
    @ResponseStatus(HttpStatus.OK)
    public InvoiceViewModel getInvoiceById(@PathVariable int id) {
        // Handle IVM not found
        InvoiceViewModel foundIvm = serviceLayer.findInvoice(id);
        if (foundIvm == null) {
            throw new IllegalArgumentException("Invoice Error: Non-existent invoice.");
        }
        return foundIvm;
    }

    // Find invoices by customer name.
    @GetMapping("/invoice/name/{name}")
    @ResponseStatus(HttpStatus.OK)
    public List<InvoiceViewModel> getInvoiceByName(@PathVariable String name) {
        // TODO: Comes back empty if no invoice found by name. Should we throw error?
        return serviceLayer.findInvoicesByName(name);
    }

    // Get all invoices.
    @GetMapping("/invoice")
    @ResponseStatus(HttpStatus.OK)
    public List<InvoiceViewModel> getInvoices() {
        return serviceLayer.findAllInvoices();
    }

    // Update an existing invoice.
    @PutMapping("/invoice")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateInvoice(@RequestBody InvoiceViewModel invoice) {
        // Handle IVM not found. Handled by check inside getInvoiceById method.
        InvoiceViewModel ivmToUpdate = getInvoiceById(invoice.getId());
        // Updated if found.
        serviceLayer.updateInvoice(ivmToUpdate);
    }

    // Delete an existing invoice.
    @DeleteMapping("/invoice/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInvoice(@PathVariable int id) {
        // Handle IVM not found. Handled by check inside getInvoiceById method.
        InvoiceViewModel ivmToDelete = getInvoiceById(id);
        // Deleted if found.
        serviceLayer.removeInvoice(ivmToDelete.getId());
    }
}
