package com.company.gamestore.controller;

import com.company.gamestore.service.ServiceLayer;
import com.company.gamestore.viewmodel.GameViewModel;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(GameController.class)
public class GameControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ServiceLayer serviceLayer;



    // ObjectMapper used to convert Java objects to JSON and vice versa
    private ObjectMapper mapper = new ObjectMapper();

    // Testing POST /Console
    @Test
    public void shouldReturnNewGameOnPostRequest() throws Exception {

        GameViewModel input_game = new GameViewModel();
        input_game.setId(1);
        input_game.setTitle("Halo");
        input_game.setStudio("Bungie");
        input_game.setQuantity(42);
        input_game.setPrice(new BigDecimal("60.00"));
        input_game.setEsrbRating("M");
        input_game.setDescription("Halo is a first person shooter");



        // Convert Java Object to JSON
        String inputJson = mapper.writeValueAsString(input_game);

        GameViewModel output_game = new GameViewModel();
        output_game.setId(1);
        output_game.setTitle("Halo");
        output_game.setStudio("Bungie");
        output_game.setQuantity(42);
        output_game.setPrice(new BigDecimal("60.00"));
        output_game.setEsrbRating("M");
        output_game.setDescription("Halo is a first person shooter");

        String outputJson = mapper.writeValueAsString(output_game);

        when(serviceLayer.saveGame(input_game)).thenReturn(output_game);

        // ACT
        mockMvc.perform(
                        post("/game")                            // Perform the POST request
                                .content(inputJson)                       // Set the request body
                                .contentType(MediaType.APPLICATION_JSON)  // Tell the server it's in JSON format
                )
                .andDo(print())                                // Print results to console
                .andExpect(status().isCreated());              // ASSERT (status code is 201)
    }



    // Testing GET game/{id}
    @Test
    public void shouldReturnGameById() throws Exception {

        GameViewModel output_game = new GameViewModel();
        output_game.setId(1);
        output_game.setTitle("Halo");
        output_game.setStudio("Bungie");
        output_game.setQuantity(42);
        output_game.setPrice(new BigDecimal("60.00"));
        output_game.setEsrbRating("M");
        output_game.setDescription("Halo is a first person shooter");

        String outputJson = mapper.writeValueAsString(output_game);

        when(serviceLayer.findGame(1)).thenReturn(output_game);
        mockMvc.perform(get("/game/id/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }


    private List<GameViewModel> gameList=new ArrayList<>();
    @Test
    public void shouldReturnAllGames() throws Exception {

        GameViewModel output_game1 = new GameViewModel();
        output_game1.setId(1);
        output_game1.setTitle("Halo");
        output_game1.setStudio("Bungie");
        output_game1.setQuantity(42);
        output_game1.setPrice(new BigDecimal("60.00"));
        output_game1.setEsrbRating("M");
        output_game1.setDescription("Halo is a first person shooter");

        gameList.add(output_game1);

        GameViewModel output_game2 = new GameViewModel();
        output_game2.setId(2);
        output_game2.setTitle("Modern Warfare 2");
        output_game2.setStudio("Infinity Ward");
        output_game2.setQuantity(80);
        output_game2.setPrice(new BigDecimal("60.00"));
        output_game2.setEsrbRating("M");
        output_game2.setDescription("Modern Warfare 2 is a first person shooter");

        gameList.add(output_game2);

        String outputJson = mapper.writeValueAsString(gameList);

        when(serviceLayer.findAllGames()).thenReturn(gameList);
        mockMvc.perform(get("/game"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    // Testing GET game/{studio}
    private List<GameViewModel> second_game_list=new ArrayList<>();
    @Test
    public void shouldReturnGamesByStudio() throws Exception {

        GameViewModel output_game = new GameViewModel();
        output_game.setId(1);
        output_game.setTitle("Halo");
        output_game.setStudio("Bungie");
        output_game.setQuantity(42);
        output_game.setPrice(new BigDecimal("60.00"));
        output_game.setEsrbRating("M");
        output_game.setDescription("Halo is a first person shooter");

        second_game_list.add(output_game);

        String outputJson = mapper.writeValueAsString(second_game_list);

        when(serviceLayer.findGamesByStudio("Bungie")).thenReturn(second_game_list);
        mockMvc.perform(get("/game/studio/Bungie"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    // Testing GET game/{esrb}
    private List<GameViewModel> third_game_list=new ArrayList<>();
    @Test
    public void shouldReturnGamesByEsrbRating() throws Exception {

        GameViewModel output_game = new GameViewModel();
        output_game.setId(1);
        output_game.setTitle("Halo");
        output_game.setStudio("Bungie");
        output_game.setQuantity(42);
        output_game.setPrice(new BigDecimal("60.00"));
        output_game.setEsrbRating("M");
        output_game.setDescription("Halo is a first person shooter");

        third_game_list.add(output_game);

        String outputJson = mapper.writeValueAsString(third_game_list);

        when(serviceLayer.findGamesByEsrbRating("M")).thenReturn(third_game_list);
        mockMvc.perform(get("/game/esrb/M"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    // Testing GET game/{title}
    private List<GameViewModel> fourth_game_list=new ArrayList<>();
    @Test
    public void shouldReturnGamesByTitle() throws Exception {

        GameViewModel output_game = new GameViewModel();
        output_game.setId(1);
        output_game.setTitle("Halo");
        output_game.setStudio("Bungie");
        output_game.setQuantity(42);
        output_game.setPrice(new BigDecimal("60.00"));
        output_game.setEsrbRating("M");
        output_game.setDescription("Halo is a first person shooter");

        fourth_game_list.add(output_game);

        String outputJson = mapper.writeValueAsString(fourth_game_list);

        when(serviceLayer.findGamesByTitle("Halo")).thenReturn(fourth_game_list);
        mockMvc.perform(get("/game/title/Halo"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldHandleInvalidId() throws Exception {

        GameViewModel output_game = new GameViewModel();
        output_game.setId(1);
        output_game.setTitle("Halo");
        output_game.setStudio("Bungie");
        output_game.setQuantity(42);
        output_game.setPrice(new BigDecimal("60.00"));
        output_game.setEsrbRating("M");
        output_game.setDescription("Halo is a first person shooter");

        String outputJson = mapper.writeValueAsString(output_game);

        when(serviceLayer.findGame(1)).thenReturn(output_game);
        mockMvc.perform(get("/console/5487545454555"))
                .andDo(print())
                .andExpect(status().is4xxClientError());

    }

}