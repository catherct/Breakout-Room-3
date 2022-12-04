package com.company.gamestore.repository;

import com.company.gamestore.model.Game;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class GameControllerRepositoryTest {

    @Autowired
    GameControllerRepository gameRepo;

    @Before
    public void setUp() throws Exception{
        gameRepo.deleteAll();
    }

    @Test
    public void addGetDeleteGameTest(){

        Game game = new Game();
        game.setTitle("Halo");
        game.setStudio("Bungie");
        game.setQuantity(42);
        game.setPrice(new BigDecimal("60.00"));
        game.setEsrbRating("M");
        game.setDescription("Halo is a first person shooter");

        game = gameRepo.save(game);

        Optional<Game> newGame = gameRepo.findById(game.getId());

        if(newGame.isPresent()){
            assertEquals( game, newGame.get());
        }
        else{
            fail();
        }

        gameRepo.deleteById(game.getId());

        newGame = gameRepo.findById(game.getId());

        assertFalse(newGame.isPresent());
    }

    @Test
    public void testGetAllGamesTest(){
        Game game = new Game();
        game.setTitle("Halo");
        game.setStudio("Bungie");
        game.setQuantity(42);
        game.setPrice(new BigDecimal("60.00"));
        game.setEsrbRating("M");
        game.setDescription("Halo is a first person shooter");

        Game game2 = new Game();
        game2.setTitle("Modern Warfare 2");
        game2.setStudio("Infinity Ward");
        game2.setQuantity(80);
        game2.setPrice(new BigDecimal("60.00"));
        game2.setEsrbRating("M");
        game2.setDescription("Modern Warfare 2 is a first person shooter");

        List<Game> games = new ArrayList<>();
        game = gameRepo.save(game);
        game2 = gameRepo.save(game2);

        games.add(game);
        games.add(game2);

        List<Game> result = gameRepo.findAll();

        assertEquals(games, result);

    }

    @Test
    public void updateGameTest(){
        Game game = new Game();
        game.setTitle("Halo");
        game.setStudio("Bungie");
        game.setQuantity(42);
        game.setPrice(new BigDecimal("60.00"));
        game.setEsrbRating("M");
        game.setDescription("Halo is a first person shooter");

        game = gameRepo.save(game);

        Integer id = game.getId();

        game.setTitle("Battlefield");
        gameRepo.save(game);

        Optional<Game> result = gameRepo.findById(id);

        if(result.isPresent()){
            assertEquals("Battlefield", result.get().getTitle());
        }
        else{
            fail();
        }
    }

    @Test
    public void getGamesByTitleTest(){
        Game game = new Game();
        game.setTitle("Halo");
        game.setStudio("Bungie");
        game.setQuantity(42);
        game.setPrice(new BigDecimal("60.00"));
        game.setEsrbRating("M");
        game.setDescription("Halo is a first person shooter");

        game = gameRepo.save(game);

        List<Game> games = new ArrayList<>();
        games.add(game);

        List<Game> result = gameRepo.findGamesByTitle("Halo");
        assertEquals(games, result);
    }

    @Test
    public void getGamesByEsrbRatingTest(){
        Game game = new Game();
        game.setTitle("Halo");
        game.setStudio("Bungie");
        game.setQuantity(42);
        game.setPrice(new BigDecimal("60.00"));
        game.setEsrbRating("M");
        game.setDescription("Halo is a first person shooter");

        Game game2 = new Game();
        game2.setTitle("Modern Warfare 2");
        game2.setStudio("Infinity Ward");
        game2.setQuantity(80);
        game2.setPrice(new BigDecimal("60.00"));
        game2.setEsrbRating("M");
        game2.setDescription("Modern Warfare 2 is a first person shooter");

        List<Game> games = new ArrayList<>();
        game = gameRepo.save(game);
        game2 = gameRepo.save(game2);

        games.add(game);
        games.add(game2);

        List<Game> result = gameRepo.findGamesByEsrbRating("M");

        assertEquals(games, result);
    }

    @Test
    public void getGamesByStudioTest(){
        Game game = new Game();
        game.setTitle("Halo");
        game.setStudio("Bungie");
        game.setQuantity(42);
        game.setPrice(new BigDecimal("60.00"));
        game.setEsrbRating("M");
        game.setDescription("Halo is a first person shooter");

        game = gameRepo.save(game);

        List<Game> games = new ArrayList<>();
        games.add(game);

        List<Game> result = gameRepo.findGamesByStudio("Bungie");
        assertEquals(games, result);
    }

}