package com.company.gamestore.controller;

import com.company.gamestore.service.ServiceLayer;
import com.company.gamestore.viewmodel.ConsoleViewModel;
import com.company.gamestore.viewmodel.GameViewModel;
import com.company.gamestore.viewmodel.InvoiceViewModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
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

    @MockBean
    private ServiceLayer serviceLayer;

    // ObjectMapper
    private ObjectMapper mapper = new ObjectMapper();

    private List<InvoiceViewModel> invoiceList = new ArrayList<>();
    @Before
    public void setUp() { }

    // Testing POST /invoice
    @Test
    public void shouldReturnNewInvoice() throws Exception {
        // Arrange
        GameViewModel testGame = new GameViewModel();
        testGame.setId(1);
        testGame.setTitle("Halo");
        testGame.setEsrbRating("M");
        testGame.setDescription("FPS");
        testGame.setPrice(new BigDecimal("60.00"));
        testGame.setStudio("Bungie");
        testGame.setQuantity(20);

        InvoiceViewModel inputInvoice = new InvoiceViewModel();
        inputInvoice.setId(1);
        inputInvoice.setName("Jose Salgado");
        inputInvoice.setStreet("1 Irvine Ln.");
        inputInvoice.setCity("Irvine");
        inputInvoice.setState("CA");
        inputInvoice.setZipcode("92617");
        inputInvoice.setItemType("Game");
        inputInvoice.setItemId(testGame.getId());
        inputInvoice.setQuantity(1);

        String inputJson = mapper.writeValueAsString(inputInvoice);

        InvoiceViewModel outputInvoice = new InvoiceViewModel();
        outputInvoice.setId(inputInvoice.getId());
        outputInvoice.setName("Jose Salgado");
        outputInvoice.setStreet("1 Irvine Ln.");
        outputInvoice.setCity("Irvine");
        outputInvoice.setState("CA");
        outputInvoice.setZipcode("92617");
        outputInvoice.setItemType("Game");
        outputInvoice.setItemId(testGame.getId());
        outputInvoice.setQuantity(1);
        outputInvoice.setUnitPrice(new BigDecimal("60.00"));
        outputInvoice.setSubtotal(new BigDecimal("60.00"));
        outputInvoice.setTax(new BigDecimal("3.60"));
        outputInvoice.setProcessingFee(new BigDecimal("1.49"));
        outputInvoice.setTotal(new BigDecimal("65.09"));


        String outputJson = mapper.writeValueAsString(outputInvoice);

        when(serviceLayer.saveInvoice(inputInvoice)).thenReturn(outputInvoice);

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
        ConsoleViewModel testConsole = new ConsoleViewModel();
        testConsole.setId(1);
        testConsole.setModel("Xbox-360");
        testConsole.setManufacturer("Microsoft");
        testConsole.setMemory_amount("512MB");
        testConsole.setProcessor("Xenon");
        testConsole.setPrice(new BigDecimal("406.99"));
        testConsole.setQuantity(20);

        InvoiceViewModel outputInvoice = new InvoiceViewModel();
        outputInvoice.setId(1);
        outputInvoice.setName("Jose Salgado");
        outputInvoice.setStreet("1 Irvine Ln.");
        outputInvoice.setCity("Irvine");
        outputInvoice.setState("CA");
        outputInvoice.setZipcode("92617");
        outputInvoice.setItemType("Console");
        outputInvoice.setItemId(testConsole.getId());
        outputInvoice.setQuantity(1);
        outputInvoice.setUnitPrice(testConsole.getPrice());
        outputInvoice.setSubtotal(testConsole.getPrice());
        outputInvoice.setTax(new BigDecimal("24.41"));
        outputInvoice.setProcessingFee(new BigDecimal("14.99"));
        outputInvoice.setTotal(new BigDecimal("446.39"));

        invoiceList.clear();
        invoiceList.add(outputInvoice);

        InvoiceViewModel outputInvoice2 = new InvoiceViewModel();
        outputInvoice2.setId(2);
        outputInvoice2.setName("Jose Salgado");
        outputInvoice2.setStreet("1 Silver Ln.");
        outputInvoice2.setCity("Shafter");
        outputInvoice2.setState("CA");
        outputInvoice2.setZipcode("93263");
        outputInvoice2.setItemType("Console");
        outputInvoice2.setItemId(testConsole.getId());
        outputInvoice2.setQuantity(1);
        outputInvoice2.setUnitPrice(testConsole.getPrice());
        outputInvoice2.setSubtotal(testConsole.getPrice());
        outputInvoice2.setTax(new BigDecimal("24.41"));
        outputInvoice2.setProcessingFee(new BigDecimal("14.99"));
        outputInvoice2.setTotal(new BigDecimal("446.39"));

        invoiceList.add(outputInvoice2);

        String outputJson = mapper.writeValueAsString(invoiceList);

        when(serviceLayer.findAllInvoices()).thenReturn(invoiceList);

        mockMvc.perform(get("/invoice"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    // Testing GET /invoice/{id}
    @Test
    public void shouldReturnInvoiceById() throws Exception {
        ConsoleViewModel testConsole = new ConsoleViewModel();
        testConsole.setId(1);
        testConsole.setModel("Xbox-360");
        testConsole.setManufacturer("Microsoft");
        testConsole.setMemory_amount("512MB");
        testConsole.setProcessor("Xenon");
        testConsole.setPrice(new BigDecimal("406.99"));
        testConsole.setQuantity(20);

        InvoiceViewModel outputInvoice = new InvoiceViewModel();
        outputInvoice.setId(1);
        outputInvoice.setName("Jose Salgado");
        outputInvoice.setStreet("1 Irvine Ln.");
        outputInvoice.setCity("Irvine");
        outputInvoice.setState("CA");
        outputInvoice.setZipcode("92617");
        outputInvoice.setItemType("Console");
        outputInvoice.setItemId(testConsole.getId());
        outputInvoice.setQuantity(1);
        outputInvoice.setUnitPrice(testConsole.getPrice());
        outputInvoice.setSubtotal(testConsole.getPrice());
        outputInvoice.setTax(new BigDecimal("24.41"));
        outputInvoice.setProcessingFee(new BigDecimal("14.99"));
        outputInvoice.setTotal(new BigDecimal("446.39"));

        String outputJson = mapper.writeValueAsString(outputInvoice);

        when(serviceLayer.findInvoice(1)).thenReturn(outputInvoice);

        mockMvc.perform(get("/invoice/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    // Testing GET /invoice/name/{name}
    @Test
    public void shouldReturnInvoiceByName() throws Exception {
        ConsoleViewModel testConsole = new ConsoleViewModel();
        testConsole.setId(1);
        testConsole.setModel("Xbox-360");
        testConsole.setManufacturer("Microsoft");
        testConsole.setMemory_amount("512MB");
        testConsole.setProcessor("Xenon");
        testConsole.setPrice(new BigDecimal("406.99"));
        testConsole.setQuantity(20);

        InvoiceViewModel outputInvoice = new InvoiceViewModel();
        outputInvoice.setId(1);
        outputInvoice.setName("Jose Salgado");
        outputInvoice.setStreet("1 Irvine Ln.");
        outputInvoice.setCity("Irvine");
        outputInvoice.setState("CA");
        outputInvoice.setZipcode("92617");
        outputInvoice.setItemType("Console");
        outputInvoice.setItemId(testConsole.getId());
        outputInvoice.setQuantity(1);
        outputInvoice.setUnitPrice(testConsole.getPrice());
        outputInvoice.setSubtotal(testConsole.getPrice());
        outputInvoice.setTax(new BigDecimal("24.41"));
        outputInvoice.setProcessingFee(new BigDecimal("14.99"));
        outputInvoice.setTotal(new BigDecimal("446.39"));

        invoiceList.clear();
        invoiceList.add(outputInvoice);

        String outputListJson = mapper.writeValueAsString(invoiceList);

        when(serviceLayer.findInvoicesByName("Jose Salgado")).thenReturn(invoiceList);
        mockMvc.perform(get("/invoice/name/Jose Salgado"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputListJson));
    }

    @Test
    public void shouldHandleInvalidId() throws Exception {
        ConsoleViewModel testConsole = new ConsoleViewModel();
        testConsole.setId(1);
        testConsole.setModel("Xbox-360");
        testConsole.setManufacturer("Microsoft");
        testConsole.setMemory_amount("512MB");
        testConsole.setProcessor("Xenon");
        testConsole.setPrice(new BigDecimal("406.99"));
        testConsole.setQuantity(20);

        InvoiceViewModel outputInvoice = new InvoiceViewModel();
        outputInvoice.setId(1);
        outputInvoice.setName("Jose Salgado");
        outputInvoice.setStreet("1 Irvine Ln.");
        outputInvoice.setCity("Irvine");
        outputInvoice.setState("CA");
        outputInvoice.setZipcode("92617");
        outputInvoice.setItemType("Console");
        outputInvoice.setItemId(testConsole.getId());
        outputInvoice.setQuantity(1);
        outputInvoice.setUnitPrice(testConsole.getPrice());
        outputInvoice.setSubtotal(testConsole.getPrice());
        outputInvoice.setTax(new BigDecimal("24.41"));
        outputInvoice.setProcessingFee(new BigDecimal("14.99"));
        outputInvoice.setTotal(new BigDecimal("446.39"));


        String outputJson = mapper.writeValueAsString(outputInvoice);

        when(serviceLayer.findInvoice(1)).thenReturn(outputInvoice);
        mockMvc.perform(get("/invoice/5487545454555"))
                .andDo(print())
                .andExpect(status().is4xxClientError());

    }

/*    // Testing PUT /invoice
    @Test
    public void shouldUpdateInvoice() throws Exception {
        InvoiceViewModel inputInvoice = new InvoiceViewModel();
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
                put("/invoice")
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
    }*/
}