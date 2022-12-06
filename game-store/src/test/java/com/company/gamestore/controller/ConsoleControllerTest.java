package com.company.gamestore.controller;

import com.company.gamestore.model.Console;
import com.company.gamestore.repository.ConsoleControllerRepository;
import com.company.gamestore.service.ServiceLayer;
import com.company.gamestore.viewmodel.ConsoleViewModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;


@RunWith(SpringRunner.class)
@WebMvcTest(ConsoleController.class)
public class ConsoleControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ServiceLayer serviceLayer;



    // ObjectMapper used to convert Java objects to JSON and vice versa
    private ObjectMapper mapper = new ObjectMapper();

    // Testing POST /Console
    @Test
    public void shouldReturnNewConsoleOnPostRequest() throws Exception {


        ConsoleViewModel input_console = new ConsoleViewModel();
        input_console.setId(1);
        input_console.setModel("Xbox-360");
        input_console.setManufacturer("Microsoft");
        input_console.setMemory_amount("512MB");
        input_console.setProcessor("Xenon");
        input_console.setPrice(new BigDecimal("406.99"));
        input_console.setQuantity(20);


        // Convert Java Object to JSON
        String inputJson = mapper.writeValueAsString(input_console);

        ConsoleViewModel output_console = new ConsoleViewModel();
        output_console.setId(1);
        output_console.setModel("Xbox-360");
        output_console.setManufacturer("Microsoft");
        output_console.setMemory_amount("512MB");
        output_console.setProcessor("Xenon");
        output_console.setPrice(new BigDecimal("406.99"));
        output_console.setQuantity(20);

        String outputJson = mapper.writeValueAsString(output_console);

        when(serviceLayer.saveConsole(input_console)).thenReturn(output_console);

        // ACT
        mockMvc.perform(
                        post("/console")                            // Perform the POST request
                                .content(inputJson)                       // Set the request body
                                .contentType(MediaType.APPLICATION_JSON)  // Tell the server it's in JSON format
                )
                .andDo(print())                                // Print results to console
                .andExpect(status().isCreated());              // ASSERT (status code is 201)
    }



    // Testing GET console/{id}
    @Test
    public void shouldReturnConsoleById() throws Exception {

        ConsoleViewModel output_console = new ConsoleViewModel();
        output_console.setId(1);
        output_console.setModel("Xbox-360");
        output_console.setManufacturer("Microsoft");
        output_console.setMemory_amount("512MB");
        output_console.setProcessor("Xenon");
        output_console.setPrice(new BigDecimal("406.99"));
        output_console.setQuantity(20);

        String outputJson = mapper.writeValueAsString(output_console);

        when(serviceLayer.findConsole(1)).thenReturn(output_console);
        mockMvc.perform(get("/console/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    // Testing GET allConsole

    private List<ConsoleViewModel> consoleList=new ArrayList<>();
    @Test
    public void shouldReturnAllConsoles() throws Exception {

        ConsoleViewModel output_console1 = new ConsoleViewModel();
        output_console1.setId(1);
        output_console1.setModel("Xbox-360");
        output_console1.setManufacturer("Microsoft");
        output_console1.setMemory_amount("512MB");
        output_console1.setProcessor("Xenon");
        output_console1.setPrice(new BigDecimal("406.99"));
        output_console1.setQuantity(20);

        consoleList.add(output_console1);

        ConsoleViewModel output_console2 = new ConsoleViewModel();
        output_console2.setId(2);
        output_console2.setModel("Play Station");
        output_console2.setManufacturer("Sony");
        output_console2.setMemory_amount("1GB");
        output_console2.setProcessor("Xenon");
        output_console2.setPrice(new BigDecimal("600.00"));
        output_console2.setQuantity(15);

        consoleList.add(output_console2);

        String outputJson = mapper.writeValueAsString(consoleList);

        when(serviceLayer.findAllConsoles()).thenReturn(consoleList);
        mockMvc.perform(get("/console"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    // Testing GET console/{Manufacturer}
    private List<ConsoleViewModel> second_console_list=new ArrayList<>();
    @Test
    public void shouldReturnConsoleByManufacturer() throws Exception {

        ConsoleViewModel output_console1 = new ConsoleViewModel();
        output_console1.setId(1);
        output_console1.setModel("Xbox-360");
        output_console1.setManufacturer("Microsoft");
        output_console1.setMemory_amount("512MB");
        output_console1.setProcessor("Xenon");
        output_console1.setPrice(new BigDecimal("406.99"));
        output_console1.setQuantity(20);

        second_console_list.add(output_console1);

        String outputJson = mapper.writeValueAsString(second_console_list);

        when(serviceLayer.findConsoleByManufacturer("Microsoft")).thenReturn(second_console_list);
        mockMvc.perform(get("/console/manufacturer/Microsoft"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }


    @Test
    public void shouldHandleInvalidId() throws Exception {

        ConsoleViewModel output_console = new ConsoleViewModel();
        output_console.setId(1);
        output_console.setModel("Xbox-360");
        output_console.setManufacturer("Microsoft");
        output_console.setMemory_amount("512MB");
        output_console.setProcessor("Xenon");
        output_console.setPrice(new BigDecimal("406.99"));
        output_console.setQuantity(20);

        String outputJson = mapper.writeValueAsString(output_console);

        when(serviceLayer.findConsole(1)).thenReturn(output_console);
        mockMvc.perform(get("/console/5487545454555"))
                .andDo(print())
                .andExpect(status().is4xxClientError());

    }
}