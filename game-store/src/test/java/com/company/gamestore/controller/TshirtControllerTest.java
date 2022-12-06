package com.company.gamestore.controller;

import com.company.gamestore.viewmodel.TshirtViewModel;
import com.company.gamestore.service.ServiceLayer;
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
@WebMvcTest(TshirtController.class)
public class TshirtControllerTest {

    // wiring in the MockMVC project
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ServiceLayer serviceLayer;

    // ObjectMapper used to convert Java objects to JSON and vice versa
    private ObjectMapper mapper = new ObjectMapper();

    // lists of shirts for testing purposes
    private List<TshirtViewModel> shirtList = new ArrayList<>();
    private List<TshirtViewModel> largeShirts = new ArrayList<>();
    private List<TshirtViewModel> blueShirts = new ArrayList<>();


    @Before
    public void setUp() {}

    // testing POST /tshirt
    @Test
    public void shouldReturnNewShirtOnPostRequest() throws Exception {

        // arrange
        TshirtViewModel inputShirt = new TshirtViewModel();
        inputShirt.setSize("large");
        inputShirt.setColor("blue");
        inputShirt.setDescription("large blue shirt");
        inputShirt.setPrice(BigDecimal.valueOf(13.99));
        inputShirt.setQuantity(2);

        // convert Java object to JSON
        String inputJson = mapper.writeValueAsString(inputShirt);

        TshirtViewModel outputShirt = new TshirtViewModel();
        outputShirt.setSize("large");
        outputShirt.setColor("blue");
        outputShirt.setDescription("large blue shirt");
        outputShirt.setPrice(BigDecimal.valueOf(13.99));
        outputShirt.setQuantity(2);
        outputShirt.setId(2);
        
        String outputJson = mapper.writeValueAsString(outputShirt);

        when(serviceLayer.saveTShirt(inputShirt)).thenReturn(outputShirt);
        // act
        mockMvc.perform(
                        post("/tshirt")
                                .content(outputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated());   // HTTP STATUS 201

    }

    // testing GET /tshirt/{id}
    @Test
    public void shouldReturnShirtById() throws Exception {

        // arrange
        TshirtViewModel outputShirt = new TshirtViewModel();
        outputShirt.setId(2);
        outputShirt.setSize("large");
        outputShirt.setColor("blue");
        outputShirt.setDescription("large blue shirt");
        outputShirt.setPrice(BigDecimal.valueOf(13.99));
        outputShirt.setQuantity(2);

        String outputJson = mapper.writeValueAsString(outputShirt);

        when(serviceLayer.findTShirt(2)).thenReturn(outputShirt);

        // act
        mockMvc.perform(get("/tshirt/2"))
                .andDo(print())
                .andExpect(status().isOk())         // HTTP STATUS 200
                .andExpect(content().json(outputJson));
    }

    // testing GET /tshirt/{color}
    @Test
    public void shouldReturnShirtsByColor() throws Exception {
        
        // arrange
        TshirtViewModel shirt1 = new TshirtViewModel();
        shirt1.setId(2);
        shirt1.setSize("large");
        shirt1.setColor("blue");
        shirt1.setDescription("large blue shirt");
        shirt1.setPrice(BigDecimal.valueOf(13.99));
        shirt1.setQuantity(2);
        blueShirts.add(shirt1);

        TshirtViewModel shirt2 = new TshirtViewModel();
        shirt2.setId(4);
        shirt2.setSize("small");
        shirt2.setColor("blue");
        shirt2.setDescription("small blue shirt");
        shirt2.setPrice(BigDecimal.valueOf(13.99));
        shirt2.setQuantity(2);
        blueShirts.add(shirt2);
        
        // convert Java object to JSON
        String outputJson = mapper.writeValueAsString(blueShirts);

        when(serviceLayer.findTShirtsByColor("blue")).thenReturn(blueShirts);

        // act
        mockMvc.perform(get("/tshirt/color/blue"))
                .andDo(print())
                .andExpect(status().isOk())         // HTTP STATUS 200
                .andExpect(content().json(outputJson));
    }

    // testing GET /tshirt/{size}
    @Test
    public void shouldReturnShirtsBySize() throws Exception {

        // arrange
        TshirtViewModel shirt1 = new TshirtViewModel();
        shirt1.setId(6);
        shirt1.setSize("large");
        shirt1.setColor("blue");
        shirt1.setDescription("large blue shirt");
        shirt1.setPrice(BigDecimal.valueOf(13.99));
        shirt1.setQuantity(2);
        largeShirts.add(shirt1);

        TshirtViewModel shirt2 = new TshirtViewModel();
        shirt2.setId(5);
        shirt2.setSize("large");
        shirt2.setColor("red");
        shirt2.setDescription("large red shirt");
        shirt2.setPrice(BigDecimal.valueOf(13.99));
        shirt2.setQuantity(2);
        largeShirts.add(shirt2);
        
        // convert Java object to JSON
        String outputJson = mapper.writeValueAsString(largeShirts);

        when(serviceLayer.findTShirtsBySize("large")).thenReturn(largeShirts);

        // act
        mockMvc.perform(get("/tshirt/size/large"))
                .andDo(print())
                .andExpect(status().isOk())    // HTTP STATUS 200
                .andExpect(content().json(outputJson));    
    }

    // testing GET /tshirt
    @Test
    public void shouldFindAllShirts() throws Exception {

        // arrange
        TshirtViewModel shirt1 = new TshirtViewModel();
        shirt1.setId(2);
        shirt1.setSize("large");
        shirt1.setColor("blue");
        shirt1.setDescription("large blue shirt");
        shirt1.setPrice(BigDecimal.valueOf(13.99));
        shirt1.setQuantity(2);
        shirtList.add(shirt1);

        TshirtViewModel shirt2 = new TshirtViewModel();
        shirt2.setId(4);
        shirt2.setSize("small");
        shirt2.setColor("blue");
        shirt2.setDescription("small blue shirt");
        shirt2.setPrice(BigDecimal.valueOf(13.99));
        shirt2.setQuantity(2);
        shirtList.add(shirt2);

        // convert Java object to JSON
        String outputJson = mapper.writeValueAsString(shirtList);

        when(serviceLayer.findAllTShirts()).thenReturn(shirtList);

        // act
        mockMvc.perform(get("/tshirt"))
                .andDo(print())
                .andExpect(status().isOk())    // HTTP STATUS 200
                .andExpect(content().json(outputJson));
    }


    @Test
    public void shouldHandleInvalidId() throws Exception {

        TshirtViewModel outputShirt = new TshirtViewModel();
        outputShirt.setId(2);
        outputShirt.setSize("large");
        outputShirt.setColor("blue");
        outputShirt.setDescription("large blue shirt");
        outputShirt.setPrice(BigDecimal.valueOf(13.99));
        outputShirt.setQuantity(2);

        String outputJson = mapper.writeValueAsString(outputShirt);

        when(serviceLayer.findTShirt(2)).thenReturn(outputShirt);

        mockMvc.perform(get("/tshirt/5487545454555"))
                .andDo(print())
                .andExpect(status().is4xxClientError());

    }

}