package com.company.gamestore.service;

import com.company.gamestore.model.Game;
import com.company.gamestore.repository.*;
import com.company.gamestore.viewmodel.GameViewModel;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class ServiceLayerTest {

    ServiceLayer service;
    ConsoleControllerRepository consoleControllerRepository;
    GameControllerRepository gameControllerRepository;
    InvoiceRepository invoiceRepository;
    ProcessingFeesRepository processingFeesRepository;
    SalesTaxRateRepository salesTaxRateRepository;
    TshirtRepository tshirtRepository;

    @Before
    public void setUp() throws Exception{
        //we will each att our set up mock to this funciton
        setUpGameRepositoryMock();
        service = new ServiceLayer(consoleControllerRepository,gameControllerRepository,invoiceRepository,
                processingFeesRepository,salesTaxRateRepository,tshirtRepository);
    }

    private void setUpGameRepositoryMock(){
        gameControllerRepository = mock(GameControllerRepository.class);
        Game game = new Game();
        game.setId(1);
        game.setTitle("Halo");
        game.setStudio("Bungie");
        game.setQuantity(42);
        game.setPrice(new BigDecimal(60));
        game.setEsrbRating("M");
        game.setDescription("Halo is a first person shooter");

        Game game2 = new Game();
        game2.setId(2);
        game2.setTitle("Modern Warfare 2");
        game2.setStudio("Infinity Ward");
        game2.setQuantity(80);
        game2.setPrice(new BigDecimal(60));
        game2.setEsrbRating("M");
        game2.setDescription("Modern Warfare 2 is a first person shooter");

        List<Game> games = new ArrayList<>();
        games.add(game);
        games.add(game2);

        List<Game> games2 = new ArrayList<>();
        games2.add(game);

        doReturn(game).when(gameControllerRepository).save(game);
        doReturn(Optional.of(game)).when(gameControllerRepository).findById(1);
        doReturn(games).when(gameControllerRepository).findAll();
        doReturn(games).when(gameControllerRepository).findGamesByEsrbRating("M");
        doReturn(games2).when(gameControllerRepository).findGamesByStudio("Bungie");
        doReturn(games2).when(gameControllerRepository).findGamesByTitle("Halo");

    }

    @Test
    public void shouldSaveGame(){
        GameViewModel compare = new GameViewModel();
        compare.setId(1);
        compare.setTitle("Halo");
        compare.setStudio("Bungie");
        compare.setQuantity(42);
        compare.setPrice(new BigDecimal(60));
        compare.setEsrbRating("M");
        compare.setDescription("Halo is a first person shooter");

        GameViewModel viewModel = service.saveGame(compare);
        assertEquals(viewModel, compare);

    }

    @Test
    public void shouldFindGame(){
        GameViewModel compare = new GameViewModel();
        compare.setId(1);
        compare.setTitle("Halo");
        compare.setStudio("Bungie");
        compare.setQuantity(42);
        compare.setPrice(new BigDecimal(60));
        compare.setEsrbRating("M");
        compare.setDescription("Halo is a first person shooter");


        GameViewModel received = service.findGame(1);

        assertEquals(compare, received);
    }
    @Test
    public void shouldFindAllGames(){
        GameViewModel compare = new GameViewModel();
        compare.setId(1);
        compare.setTitle("Halo");
        compare.setStudio("Bungie");
        compare.setQuantity(42);
        compare.setPrice(new BigDecimal(60));
        compare.setEsrbRating("M");
        compare.setDescription("Halo is a first person shooter");

        GameViewModel compare2 = new GameViewModel();
        compare2.setId(2);
        compare2.setTitle("Modern Warfare 2");
        compare2.setStudio("Infinity Ward");
        compare2.setQuantity(80);
        compare2.setPrice(new BigDecimal(60));
        compare2.setEsrbRating("M");
        compare2.setDescription("Modern Warfare 2 is a first person shooter");

        List<GameViewModel> validateModels = new ArrayList<>();
        validateModels.add(compare);
        validateModels.add(compare2);

        List<GameViewModel> receivedModels = service.findAllGames();

        assertEquals(validateModels, receivedModels);
    }

    @Test
    public void shouldFindGamesByEsrbRating(){
        GameViewModel compare = new GameViewModel();
        compare.setId(1);
        compare.setTitle("Halo");
        compare.setStudio("Bungie");
        compare.setQuantity(42);
        compare.setPrice(new BigDecimal(60));
        compare.setEsrbRating("M");
        compare.setDescription("Halo is a first person shooter");

        GameViewModel compare2 = new GameViewModel();
        compare2.setId(2);
        compare2.setTitle("Modern Warfare 2");
        compare2.setStudio("Infinity Ward");
        compare2.setQuantity(80);
        compare2.setPrice(new BigDecimal(60));
        compare2.setEsrbRating("M");
        compare2.setDescription("Modern Warfare 2 is a first person shooter");

        List<GameViewModel> validateModels = new ArrayList<>();
        validateModels.add(compare);
        validateModels.add(compare2);

        List<GameViewModel> receivedModels = service.findGamesByEsrbRating("M");

        assertEquals(validateModels, receivedModels);

    }

    @Test
    public void shouldFindGamesByStudio(){
        GameViewModel compare = new GameViewModel();
        compare.setId(1);
        compare.setTitle("Halo");
        compare.setStudio("Bungie");
        compare.setQuantity(42);
        compare.setPrice(new BigDecimal(60));
        compare.setEsrbRating("M");
        compare.setDescription("Halo is a first person shooter");
        List<GameViewModel> validateModels = new ArrayList<>();
        validateModels.add(compare);

        List<GameViewModel> receivedModels = service.findGamesByStudio("Bungie");

        assertEquals(validateModels, receivedModels);
    }

    @Test
    public void shouldFindGamesByTitle(){
        GameViewModel compare = new GameViewModel();
        compare.setId(1);
        compare.setTitle("Halo");
        compare.setStudio("Bungie");
        compare.setQuantity(42);
        compare.setPrice(new BigDecimal(60));
        compare.setEsrbRating("M");
        compare.setDescription("Halo is a first person shooter");
        List<GameViewModel> validateModels = new ArrayList<>();
        validateModels.add(compare);

        List<GameViewModel> receivedModels = service.findGamesByTitle("Halo");

        assertEquals(validateModels, receivedModels);
    }
}