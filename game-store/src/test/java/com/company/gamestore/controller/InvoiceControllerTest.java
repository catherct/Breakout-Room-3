package com.company.gamestore.controller;

import com.company.gamestore.model.Invoice;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(InvoiceController.class)
public class InvoiceControllerTest {
    // Wiring in the MockMvc object
    @Autowired
    private MockMvc mockMvc;

    // ObjectMapper
    private ObjectMapper mapper = new ObjectMapper();

    private List<Invoice> invoiceList;
    @Before
    public void setUp() { }

    // Testing POST /invoice
    @Test
    public void shouldReturnNewInvoice() throws Exception {
        // Arrange
        Invoice inputInvoice = new Invoice();
        inputInvoice.setName("Jose Salgado");
        inputInvoice.setStreet("1 Irvine Ln.");
        inputInvoice.setCity("Irvine");
        inputInvoice.setState("CA");
        inputInvoice.setZipcode("92617");
        inputInvoice.setItemType("Console");
        inputInvoice.setItemId(1);
        inputInvoice.setQuantity(1);

        String inputJson = mapper.writeValueAsString(inputInvoice);

        Invoice outputInvoice = new Invoice();
        inputInvoice.setName("Jose Salgado");
        inputInvoice.setStreet("1 Irvine Ln.");
        inputInvoice.setCity("Irvine");
        inputInvoice.setState("CA");
        inputInvoice.setZipcode("92617");
        inputInvoice.setItemType("Console");
        inputInvoice.setItemId(1);
        inputInvoice.setQuantity(1);

        String outputJson = mapper.writeValueAsString(outputInvoice);

        mockMvc.perform(
                post("/invoice")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isCreated());
    }

    // Testing GET /invoice
    @Test
    public void shouldReturnAllInvoices() throws Exception {
        String outputJson = mapper.writeValueAsString(invoiceList);

        mockMvc.perform(get("/invoice"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    // Testing GET /invoice/{id}
    @Test
    public void shouldReturnInvoiceById() throws Exception {
        Invoice outputInvoice = new Invoice();
        outputInvoice.setName("Jose Salgado");
        outputInvoice.setStreet("1 Irvine Ln.");
        outputInvoice.setCity("Irvine");
        outputInvoice.setState("CA");
        outputInvoice.setZipcode("92617");
        outputInvoice.setItemType("Console");
        outputInvoice.setItemId(1);
        outputInvoice.setQuantity(1);

        String outputJson = mapper.writeValueAsString(outputInvoice);

        // TODO check content()
        mockMvc.perform(get("/invoice/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    // Testing GET /invoice/name/{name}
    @Test
    public void shouldReturnInvoiceByName() throws Exception {
        // TODO go over this test
        Invoice outputInvoice = new Invoice();
        outputInvoice.setName("Jose Salgado");
        outputInvoice.setStreet("1 Irvine Ln.");
        outputInvoice.setCity("Irvine");
        outputInvoice.setState("CA");
        outputInvoice.setZipcode("92617");
        outputInvoice.setItemType("Console");
        outputInvoice.setItemId(1);
        outputInvoice.setQuantity(1);

        // compare list ???
    }

    // Testing PUT /invoice
    @Test
    public void shouldUpdateInvoice() throws Exception {
        Invoice inputInvoice = new Invoice();
        inputInvoice.setName("Jose Salgado");
        inputInvoice.setStreet("1 Silver Ln.");
        inputInvoice.setCity("Shafter");
        inputInvoice.setState("CA");
        inputInvoice.setZipcode("93263");
        inputInvoice.setItemType("Console");
        inputInvoice.setItemId(1);
        inputInvoice.setQuantity(3);
        inputInvoice.setId(1);

        String inputJson = mapper.writeValueAsString(inputInvoice);

        mockMvc.perform(
                put("invoice")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    // Testing DELETE /invoice/{id}
    @Test
    public void shouldDeleteById() throws Exception {
        mockMvc.perform(delete("/invoice/1"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}