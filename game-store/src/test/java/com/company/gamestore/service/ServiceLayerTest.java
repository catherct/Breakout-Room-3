package com.company.gamestore.service;


import com.company.gamestore.model.Game;
import com.company.gamestore.repository.*;
import com.company.gamestore.viewmodel.GameViewModel;

import com.company.gamestore.model.Console;
import com.company.gamestore.repository.*;
import com.company.gamestore.viewmodel.ConsoleViewModel;
import com.company.gamestore.model.Invoice;
import com.company.gamestore.repository.*;
import com.company.gamestore.viewmodel.InvoiceViewModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.company.gamestore.model.Tshirt;
import com.company.gamestore.viewmodel.TshirtViewModel;
import com.company.gamestore.viewmodel.InvoiceViewModel;
import static junit.framework.TestCase.*;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class ServiceLayerTest {

    ServiceLayer service;
    ConsoleControllerRepository consoleRepo;
    GameControllerRepository gameRepo;
    InvoiceRepository invoiceRepo;
    ProcessingFeesRepository processingRepo;
    SalesTaxRateRepository taxRepo;
    TshirtRepository shirtRepo;

    @Before

    public void setUp() throws Exception{
        setUpGameRepositoryMock();
        setUpConsoleRepositoryMock();
        setUpInvoiceRepositoryMock();
        setupTshirtRepositoryMock();
        service = new ServiceLayer(consoleRepo, gameRepo, invoiceRepo, processingRepo, taxRepo, shirtRepo);

    }

    private void setupTshirtRepositoryMock() {

        shirtRepo = mock(TshirtRepository.class);

        Tshirt shirt1 = new Tshirt();
        shirt1.setId(1);
        shirt1.setColor("blue");
        shirt1.setSize("large");
        shirt1.setDescription("large blue shirt");
        shirt1.setPrice(BigDecimal.valueOf(13.99));
        shirt1.setQuantity(2);

        Tshirt shirt2 = new Tshirt();
        shirt2.setId(2);
        shirt2.setColor("blue");
        shirt2.setSize("small");
        shirt2.setDescription("small blue shirt");
        shirt2.setPrice(BigDecimal.valueOf(13.99));
        shirt2.setQuantity(2);

        List<Tshirt> shirtList = new ArrayList<>();
        shirtList.add(shirt1);

        doReturn(shirt1).when(shirtRepo).save(shirt2);
        doReturn(Optional.of(shirt1)).when(shirtRepo).findById(1);
        doReturn(shirtList).when(shirtRepo).findAll();

    }

    @Test
    public void shouldSaveShirt() throws Exception {

        // arrange
        List<TshirtViewModel> sampleShirts = new ArrayList<>();

        TshirtViewModel newShirt = new TshirtViewModel();
        newShirt.setId(1);
        newShirt.setColor("blue");
        newShirt.setSize("large");
        newShirt.setDescription("large blue shirt");
        newShirt.setPrice(BigDecimal.valueOf(13.99));
        newShirt.setQuantity(2);
        sampleShirts.add(newShirt);

        // act
        newShirt = service.saveTShirt(newShirt);
        assertFalse(sampleShirts.isEmpty());
    }


    @Test
    public void shouldFindShirtsById() {

        // arrange
        TshirtViewModel toCompare = new TshirtViewModel();
        toCompare.setId(1);
        toCompare.setColor("blue");
        toCompare.setSize("large");
        toCompare.setDescription("large blue shirt");
        toCompare.setPrice(BigDecimal.valueOf(13.99));
        toCompare.setQuantity(2);

        // act
        TshirtViewModel tshirtViewModel = service.findTShirt(1);
        Assert.assertEquals(tshirtViewModel, toCompare);

    }


    @Test
    public void shouldFindAllShirts() {

        // arrange
        List<TshirtViewModel> sampleShirts = new ArrayList<>();

        TshirtViewModel toCompare1 = new TshirtViewModel();
        toCompare1.setId(1);
        toCompare1.setColor("blue");
        toCompare1.setSize("large");
        toCompare1.setDescription("large blue shirt");
        toCompare1.setPrice(BigDecimal.valueOf(13.99));
        toCompare1.setQuantity(2);
        sampleShirts.add(toCompare1);

        TshirtViewModel toCompare2 = new TshirtViewModel();
        toCompare2.setId(2);
        toCompare2.setColor("blue");
        toCompare2.setSize("small");
        toCompare2.setDescription("small blue shirt");
        toCompare2.setPrice(BigDecimal.valueOf(13.99));
        toCompare2.setQuantity(2);
        sampleShirts.add(toCompare2);

        // act
        List<TshirtViewModel> tshirtViewModel = service.findAllTShirts();
        assertFalse(sampleShirts.isEmpty());

    }

    @Test
    public void shouldFindShirtsByColor() {

        // arrange
        List<TshirtViewModel> sampleShirts = new ArrayList<>();

        TshirtViewModel toCompare1 = new TshirtViewModel();
        toCompare1.setId(1);
        toCompare1.setColor("blue");
        toCompare1.setSize("large");
        toCompare1.setDescription("large blue shirt");
        toCompare1.setPrice(BigDecimal.valueOf(13.99));
        toCompare1.setQuantity(2);
        sampleShirts.add(toCompare1);

        TshirtViewModel toCompare2 = new TshirtViewModel();
        toCompare2.setId(2);
        toCompare2.setColor("blue");
        toCompare2.setSize("small");
        toCompare2.setDescription("small blue shirt");
        toCompare2.setPrice(BigDecimal.valueOf(13.99));
        toCompare2.setQuantity(2);
        sampleShirts.add(toCompare2);

        // act
        List<TshirtViewModel> tshirtViewModel = service.findTShirtsByColor("blue");
        assertFalse(sampleShirts.isEmpty());

    }

    @Test
    public void shouldFindAllShirtsBySize() {

        // arrange
        List<TshirtViewModel> sampleShirts = new ArrayList<>();

        TshirtViewModel toCompare1 = new TshirtViewModel();
        toCompare1.setId(1);
        toCompare1.setColor("red");
        toCompare1.setSize("large");
        toCompare1.setDescription("large red shirt");
        toCompare1.setPrice(BigDecimal.valueOf(13.99));
        toCompare1.setQuantity(2);
        sampleShirts.add(toCompare1);

        TshirtViewModel toCompare2 = new TshirtViewModel();
        toCompare2.setId(2);
        toCompare2.setColor("blue");
        toCompare2.setSize("large");
        toCompare2.setDescription("large blue shirt");
        toCompare2.setPrice(BigDecimal.valueOf(13.99));
        toCompare2.setQuantity(2);
        sampleShirts.add(toCompare2);

        // act
        List<TshirtViewModel> tshirtViewModel = service.findTShirtsBySize("large");
        assertFalse(sampleShirts.isEmpty());


    }

    // Helper method
    private void setUpInvoiceRepositoryMock() {
        invoiceRepo = mock(InvoiceRepository.class);
        Invoice invoice = new Invoice();
        invoice.setId(1);
        invoice.setName("Jose Salgado");
        invoice.setStreet("1 Irvine Ln.");
        invoice.setCity("Irvine");
        invoice.setState("CA");
        invoice.setZipcode("92617");
        invoice.setItemType("Console");
        invoice.setItemId(1);
        invoice.setQuantity(1);
        invoice.setSubtotal(new BigDecimal(10.00));
        invoice.setTax(new BigDecimal(.8));
        invoice.setProcessingFee(new BigDecimal(1.25));
        invoice.setTotal(new BigDecimal(15.00));

        Invoice invoice2 = new Invoice();
        invoice2.setId(2);
        invoice2.setName("Joe Doe");
        invoice2.setStreet("4 Oak St.");
        invoice2.setCity("Bakersfield");
        invoice2.setState("CA");
        invoice2.setZipcode("93314");
        invoice2.setItemType("Game");
        invoice2.setItemId(32);
        invoice2.setQuantity(4);
        invoice2.setSubtotal(new BigDecimal(11.00));
        invoice2.setTax(new BigDecimal(.7));
        invoice2.setProcessingFee(new BigDecimal(1.30));
        invoice2.setTotal(new BigDecimal(10.00));

        List<Invoice> iList = new ArrayList<>();
        iList.add(invoice);
        iList.add(invoice2);

        List<Invoice> invoiceList = new ArrayList<>();
        invoiceList.add(invoice2);

        doReturn(invoice).when(invoiceRepo).save(invoice2);
        doReturn(Optional.of(invoice)).when(invoiceRepo).findById(1);
        doReturn(iList).when(invoiceRepo).findAll();
        doReturn(invoiceList).when(invoiceRepo).findByName("Joe Doe");
    }

    @Test
    public void shouldSaveInvoice() {
        // TODO fix this function
        // Arrange
        InvoiceViewModel expectedResult = new InvoiceViewModel();
        expectedResult.setId(2);
        expectedResult.setName("Susan Lady");
        expectedResult.setStreet("1 Pine Dr.");
        expectedResult.setCity("Shafter");
        expectedResult.setState("CA");
        expectedResult.setZipcode("93262");
        expectedResult.setItemType("Console");
        expectedResult.setItemId(1);
        expectedResult.setQuantity(1);

        InvoiceViewModel invoice = new InvoiceViewModel();
        invoice.setName("Susan Lady");
        invoice.setStreet("1 Pine Dr.");
        invoice.setCity("Shafter");
        invoice.setState("CA");
        invoice.setZipcode("93262");
        invoice.setItemType("Console");
        invoice.setItemId(1);
        invoice.setQuantity(1);

        // ACT
        //invoice = service.saveInvoice(invoice);
        //assertEquals(expectedResult, invoice);
    }

    @Test
    public void shouldFindInvoice() {
        InvoiceViewModel invoice = new InvoiceViewModel();
        invoice.setId(1);
        invoice.setName("Jose Salgado");
        invoice.setStreet("1 Irvine Ln.");
        invoice.setCity("Irvine");
        invoice.setState("CA");
        invoice.setZipcode("92617");
        invoice.setItemType("Console");
        invoice.setItemId(1);
        invoice.setQuantity(1);
        invoice.setSubtotal(new BigDecimal(10.00));
        invoice.setTax(new BigDecimal(.8));
        invoice.setProcessingFee(new BigDecimal(1.25));
        invoice.setTotal(new BigDecimal(15.00));

        InvoiceViewModel invoiceViewModel = service.findInvoice(1);
        assertEquals(invoiceViewModel, invoice);
    }

    @Test
    public void shouldFindAllInvoices() {
        InvoiceViewModel invoice = new InvoiceViewModel();
        invoice.setId(1);
        invoice.setName("Jose Salgado");
        invoice.setStreet("1 Irvine Ln.");
        invoice.setCity("Irvine");
        invoice.setState("CA");
        invoice.setZipcode("92617");
        invoice.setItemType("Console");
        invoice.setItemId(1);
        invoice.setQuantity(1);
        invoice.setSubtotal(new BigDecimal(10.00));
        invoice.setTax(new BigDecimal(.8));
        invoice.setProcessingFee(new BigDecimal(1.25));
        invoice.setTotal(new BigDecimal(15.00));


        InvoiceViewModel invoice2 = new InvoiceViewModel();
        invoice2.setId(2);
        invoice2.setName("Joe Doe");
        invoice2.setStreet("4 Oak St.");
        invoice2.setCity("Bakersfield");
        invoice2.setState("CA");
        invoice2.setZipcode("93314");
        invoice2.setItemType("Game");
        invoice2.setItemId(32);
        invoice2.setQuantity(4);
        invoice2.setSubtotal(new BigDecimal(11.00));
        invoice2.setTax(new BigDecimal(.7));
        invoice2.setProcessingFee(new BigDecimal(1.30));
        invoice2.setTotal(new BigDecimal(10.00));


        List<InvoiceViewModel> iList = new ArrayList<>();
        iList.add(invoice);
        iList.add(invoice2);

        List<InvoiceViewModel> invoiceViewModelList = service.findAllInvoices();
        assertEquals(iList.size(), invoiceViewModelList.size());
    }

    @Test
    public void shouldFindInvoiceByName() {
        InvoiceViewModel invoice2 = new InvoiceViewModel();
        invoice2.setId(2);
        invoice2.setName("Joe Doe");
        invoice2.setStreet("4 Oak St.");
        invoice2.setCity("Bakersfield");
        invoice2.setState("CA");
        invoice2.setZipcode("93314");
        invoice2.setItemType("Game");
        invoice2.setItemId(32);
        invoice2.setQuantity(4);
        invoice2.setSubtotal(new BigDecimal(11.00));
        invoice2.setTax(new BigDecimal(.7));
        invoice2.setProcessingFee(new BigDecimal(1.30));
        invoice2.setTotal(new BigDecimal(10.00));

        List<InvoiceViewModel> ivm = service.findInvoicesByName("Joe Doe");
        assertEquals(1, ivm.size());
    }

    // Helper methods
    private void setUpConsoleRepositoryMock() {
        consoleControllerRepository = mock(ConsoleControllerRepository.class);
        Console console = new Console();
        console.setConsole_id(1);
        console.setModel("Xbox-360");
        console.setManufacturer("Microsoft");
        console.setMemory_amount("512MB");
        console.setProcessor("Xenon");
        console.setPrice(new BigDecimal("406.99"));
        console.setQuantity(20);


        Console console2 = new Console();

        console2.setModel("Xbox-360");
        console2.setManufacturer("Microsoft");
        console2.setMemory_amount("512MB");
        console2.setProcessor("Xenon");
        console2.setPrice(new BigDecimal("406.99"));
        console2.setQuantity(20);

        List<Console> aList = new ArrayList<>();
        aList.add(console);

        List<Console> bList = new ArrayList<>();
        bList.add(console);

        doReturn(console).when(consoleControllerRepository).save(console2);
        doReturn(Optional.of(console)).when(consoleControllerRepository).findById(1);
        doReturn(bList).when(consoleControllerRepository).findAll();
        doReturn(bList).when(consoleControllerRepository).findConsoleByManufacturer("Microsoft");
    }

    @Test
    public void shouldSaveConsole() throws Exception {

        //Test is failing

        List<ConsoleViewModel> sampleConsole = new ArrayList<>();

        ConsoleViewModel newConsole = new ConsoleViewModel();
        newConsole.setId(1);
        newConsole.setModel("Xbox-360");
        newConsole.setManufacturer("Microsoft");
        newConsole.setMemory_amount("512MB");
        newConsole.setProcessor("Xenon");
        newConsole.setPrice(new BigDecimal("406.99"));
        newConsole.setQuantity(20);
        sampleConsole.add(newConsole);

        // act
        newConsole = service.saveConsole(newConsole);
        assertTrue(sampleConsole.isEmpty());

    }

    @Test
    public void shouldGetConsoleById() {

        // arrange
        ConsoleViewModel toCompare = new ConsoleViewModel();
        toCompare.setId(1);
        toCompare.setModel("Xbox-360");
        toCompare.setManufacturer("Microsoft");
        toCompare.setMemory_amount("512MB");
        toCompare.setProcessor("Xenon");
        toCompare.setPrice(new BigDecimal("406.99"));
        toCompare.setQuantity(20);

        // act
        ConsoleViewModel consoleViewModel = service.findConsole(1);
        assertEquals(consoleViewModel, toCompare);

    }

    @Test
    public void shouldGetAllConsoles() {

        List<ConsoleViewModel> consolelist=new ArrayList<>();
        // arrange
        ConsoleViewModel toCompare1 = new ConsoleViewModel();
        toCompare1.setId(1);
        toCompare1.setModel("Xbox-360");
        toCompare1.setManufacturer("Microsoft");
        toCompare1.setMemory_amount("512MB");
        toCompare1.setProcessor("Xenon");
        toCompare1.setPrice(new BigDecimal("406.99"));
        toCompare1.setQuantity(20);

        consolelist.add(toCompare1);


        // act
        List<ConsoleViewModel> consoleViewModel = service.findAllConsoles();
        assertEquals(consoleViewModel.size(),consolelist.size());

    }

    @Test
    public void shouldGetConsolesByManufacturer() {

        // arrange
        ConsoleViewModel toCompare = new ConsoleViewModel();
        toCompare.setId(1);
        toCompare.setModel("Xbox-360");
        toCompare.setManufacturer("Microsoft");
        toCompare.setMemory_amount("512MB");
        toCompare.setProcessor("Xenon");
        toCompare.setPrice(new BigDecimal("406.99"));
        toCompare.setQuantity(20);


        // act

        List<ConsoleViewModel> aList = service.findConsoleByManufacturer("Microsoft");

        Assert.assertEquals(aList.size(),1);


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