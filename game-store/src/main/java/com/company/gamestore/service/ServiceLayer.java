package com.company.gamestore.service;

import com.company.gamestore.model.*;
import com.company.gamestore.repository.*;
import com.company.gamestore.viewmodel.ConsoleViewModel;
import com.company.gamestore.viewmodel.GameViewModel;
import com.company.gamestore.viewmodel.InvoiceViewModel;
import com.company.gamestore.viewmodel.TshirtViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ServiceLayer {
    private ConsoleControllerRepository consoleRepo;
    private GameControllerRepository gameRepo;
    private InvoiceRepository invoiceRepo;
    private ProcessingFeesRepository processingFeesRepo;
    private SalesTaxRateRepository salesTaxRateRepo;
    private TshirtRepository tshirtRepo;

    @Autowired
    public ServiceLayer(ConsoleControllerRepository consoleRepo, GameControllerRepository gameRepo, InvoiceRepository invoiceRepo,
                        ProcessingFeesRepository processingFeesRepo, SalesTaxRateRepository salesTaxRateRepo, TshirtRepository tshirtRepo){
        this.consoleRepo = consoleRepo;
        this.gameRepo = gameRepo;
        this.invoiceRepo = invoiceRepo;
        this.processingFeesRepo = processingFeesRepo;
        this.salesTaxRateRepo = salesTaxRateRepo;
        this.tshirtRepo = tshirtRepo;
    }


    //helper function to build a console view model
    private ConsoleViewModel buildConsoleViewModel(Console console){
        ConsoleViewModel consoleViewModel = new ConsoleViewModel();
        consoleViewModel.setModel(console.getModel());
        consoleViewModel.setId(console.getConsole_id());
        consoleViewModel.setManufacturer(console.getManufacturer());
        consoleViewModel.setPrice(console.getPrice());
        consoleViewModel.setQuantity(console.getQuantity());
        consoleViewModel.setProcessor(console.getProcessor());
        consoleViewModel.setMemory_amount(console.getMemory_amount());
        return consoleViewModel;
    }

    //helper function to build a game view model
    private GameViewModel buildGameViewModel(Game game){
        GameViewModel gameViewModel = new GameViewModel();

        gameViewModel.setDescription(game.getDescription());
        gameViewModel.setEsrbRating(game.getEsrbRating());
        gameViewModel.setId(game.getId());
        gameViewModel.setPrice(game.getPrice());
        gameViewModel.setQuantity(game.getQuantity());
        gameViewModel.setStudio(game.getStudio());
        gameViewModel.setTitle(game.getTitle());
        return gameViewModel;
    }

    //helper function to build an invoice view model
    private InvoiceViewModel buildInvoiceViewModel(Invoice invoice){
        InvoiceViewModel invoiceViewModel = new InvoiceViewModel();

        //should be 14 total
        invoiceViewModel.setId(invoice.getId());
        invoiceViewModel.setCity(invoice.getCity());
        invoiceViewModel.setQuantity(invoice.getQuantity());
        invoiceViewModel.setName(invoice.getName());
        invoiceViewModel.setItemType(invoice.getItemType());
        invoiceViewModel.setProcessingFee(invoice.getProcessingFee());
        invoiceViewModel.setState(invoice.getState());
        invoiceViewModel.setStreet(invoice.getStreet());
        invoiceViewModel.setZipcode(invoice.getZipcode());
        invoiceViewModel.setTax(invoice.getTax());
        invoiceViewModel.setUnitPrice(invoice.getUnitPrice());
        invoiceViewModel.setSubtotal(invoice.getSubtotal());
        invoiceViewModel.setItemId(invoice.getItemId());
        invoiceViewModel.setTotal(invoice.getTotal());

        return invoiceViewModel;
    }

    //helper function to build a tshirt view model
    private TshirtViewModel buildTshirtViewModel(Tshirt tshirt){
        TshirtViewModel tshirtViewModel = new TshirtViewModel();

        tshirtViewModel.setColor(tshirt.getColor());
        tshirtViewModel.setDescription(tshirt.getDescription());
        tshirtViewModel.setId(tshirt.getId());
        tshirtViewModel.setQuantity(tshirt.getQuantity());
        tshirtViewModel.setSize(tshirt.getSize());
        tshirtViewModel.setPrice(tshirt.getPrice());
        return tshirtViewModel;
    }

    //helper function to build a console
    private Console buildConsole(ConsoleViewModel viewModel){
        Console console = new Console();
        console.setModel(viewModel.getModel());
        console.setManufacturer(viewModel.getManufacturer());
        console.setPrice(viewModel.getPrice());
        console.setQuantity(viewModel.getQuantity());
        console.setProcessor(viewModel.getProcessor());
        console.setMemory_amount(viewModel.getMemory_amount());
        return console;
    }

    //helper function to build a game
    private Game buildGame(GameViewModel viewModel){
        Game game = new Game();
        game.setDescription(viewModel.getDescription());
        game.setEsrbRating(viewModel.getEsrbRating());
        game.setPrice(viewModel.getPrice());
        game.setQuantity(viewModel.getQuantity());
        game.setStudio(viewModel.getStudio());
        game.setTitle(viewModel.getTitle());
        return game;
    }

    //helper function to build a tshirt
    private Tshirt buildTshirt(TshirtViewModel viewModel){
        Tshirt tshirt = new Tshirt();

        tshirt.setColor(viewModel.getColor());
        tshirt.setDescription(viewModel.getDescription());
        tshirt.setQuantity(viewModel.getQuantity());
        tshirt.setSize(viewModel.getSize());
        tshirt.setPrice(viewModel.getPrice());

        return tshirt;
    }

    //helper function to build an invoice
    //only sets the data for what we will be given and not the
    //calculated values.
    private Invoice buildInvoice(InvoiceViewModel viewModel){

        Invoice invoice = new Invoice();

        invoice.setCity(viewModel.getCity());
        invoice.setQuantity(viewModel.getQuantity());
        invoice.setName(viewModel.getName());
        invoice.setItemType(viewModel.getItemType());
        invoice.setState(viewModel.getState());
        invoice.setStreet(viewModel.getStreet());
        invoice.setZipcode(viewModel.getZipcode());
        invoice.setItemId(viewModel.getItemId());
        invoice.setId(viewModel.getId());
        invoice.setProcessingFee(viewModel.getProcessingFee());
        invoice.setTax(viewModel.getTax());
        invoice.setUnitPrice(viewModel.getUnitPrice());
        invoice.setSubtotal(viewModel.getSubtotal());
        invoice.setTotal(viewModel.getTotal());

        return invoice;
    }
    //Console Api

    @Transactional
    public ConsoleViewModel saveConsole(ConsoleViewModel viewModel){
        Console console = buildConsole(viewModel);

        console =  consoleRepo.save(console);
        viewModel.setId(console.getConsole_id());
        return viewModel;
    }

    public ConsoleViewModel findConsole(Integer id){
        Optional<Console> console = consoleRepo.findById(id);

        //if the console is present we send that if not we return null
        return console.isPresent() ? buildConsoleViewModel(console.get()) : null;
    }

    public List<ConsoleViewModel> findAllConsoles(){
        List<Console> consoleList = consoleRepo.findAll();
        List<ConsoleViewModel> consoleViewModelList = new ArrayList<>();

        //we iterate through the list of consoles to build the view models for the list
        for( Console console: consoleList){
            ConsoleViewModel consoleViewModel = buildConsoleViewModel(console);
            consoleViewModelList.add(consoleViewModel);
        }

        return consoleViewModelList;
    }

    @Transactional
    public void updateConsole(ConsoleViewModel viewModel){
        Console console = buildConsole(viewModel);

        console.setConsole_id(viewModel.getId());

        consoleRepo.save(console);

    }

    @Transactional
    public void removeConsole(Integer id){
        consoleRepo.deleteById(id);
    }

    public List<ConsoleViewModel> findConsoleByManufacturer(String manufacturer){
        List<Console> consoleList = consoleRepo.findConsoleByManufacturer(manufacturer);
        List<ConsoleViewModel> consoleViewModelList = new ArrayList<>();

        //builds the list of consoleViewModels
        for( Console console: consoleList){
            ConsoleViewModel consoleViewModel = buildConsoleViewModel(console);
            consoleViewModelList.add(consoleViewModel);
        }

        return consoleViewModelList;
    }

    //GAME API
    @Transactional
    public GameViewModel saveGame(GameViewModel viewModel){
        //builds the game
        Game game = buildGame(viewModel);

        //saves the game
        game = gameRepo.save(game);

        //sets the id of the view model for the newly saved game
        viewModel.setId(game.getId());

        return  viewModel;

    }

    public GameViewModel findGame(Integer id){
        Optional<Game> game = gameRepo.findById(id);

        //if the game is present we send that if not we return null
        return game.isPresent() ? buildGameViewModel(game.get()) : null;
    }

    public List<GameViewModel> findAllGames(){
        List<Game> gameList = gameRepo.findAll();
        List<GameViewModel> gameViewModelList = new ArrayList<>();

        //we iterate through the list of games to build the view models for the list
        for(Game game: gameList){
            GameViewModel gameViewModel = buildGameViewModel(game);
            gameViewModelList.add(gameViewModel);
        }

        return gameViewModelList;

    }

    @Transactional
    public void updateGame(GameViewModel viewModel){
        Game game = buildGame(viewModel);
        game.setId(viewModel.getId());
        gameRepo.save(game);
    }

    @Transactional
    public void deleteGame(Integer id){
        gameRepo.deleteById(id);
    }

    public List<GameViewModel> findGamesByStudio(String studio){
        List<Game> gameList = gameRepo.findGamesByStudio(studio);
        List<GameViewModel> gameViewModelList = new ArrayList<>();

        //we iterate through the list of games to build the view models for the list
        for(Game game: gameList){
            GameViewModel gameViewModel = buildGameViewModel(game);
            gameViewModelList.add(gameViewModel);
        }
        return gameViewModelList;
    }

    public List<GameViewModel> findGamesByEsrbRating(String esrbRating){
        List<Game> gameList = gameRepo.findGamesByEsrbRating(esrbRating);
        List<GameViewModel> gameViewModelList = new ArrayList<>();

        //we iterate through the list of games to build the view models for the list
        for(Game game: gameList){
            GameViewModel gameViewModel = buildGameViewModel(game);
            gameViewModelList.add(gameViewModel);
        }
        return gameViewModelList;
    }

    public List<GameViewModel> findGamesByTitle(String title){
        List<Game> gameList = gameRepo.findGamesByTitle(title);
        List<GameViewModel> gameViewModelList = new ArrayList<>();

        //we iterate through the list of games to build the view models for the list
        for(Game game: gameList){
            GameViewModel gameViewModel = buildGameViewModel(game);
            gameViewModelList.add(gameViewModel);
        }
        return gameViewModelList;
    }

    //T-SHIRT API
    @Transactional
    public TshirtViewModel saveTShirt(TshirtViewModel viewModel){
        Tshirt tshirt = buildTshirt(viewModel);
        tshirt = tshirtRepo.save(tshirt);
        viewModel.setId(tshirt.getId());

        return viewModel;
    }

    public TshirtViewModel findTShirt(Integer id){
        Optional<Tshirt> tshirt = tshirtRepo.findById(id);

        //if the tshirt is present we send that if not we return null
        return tshirt.isPresent() ? buildTshirtViewModel(tshirt.get()): null;
    }

    public List<TshirtViewModel> findAllTShirts(){
        List<Tshirt> tshirtList = tshirtRepo.findAll();
        List<TshirtViewModel> tshirtViewModels = new ArrayList<>();

        //we iterate through the list of t-shirts to build the view models for the list
        for(Tshirt tshirt: tshirtList){
            TshirtViewModel tshirtViewModel = buildTshirtViewModel(tshirt);
            tshirtViewModels.add(tshirtViewModel);
        }

        return tshirtViewModels;
    }

    @Transactional
    public void updateTShirt(TshirtViewModel viewModel){
        Tshirt tshirt = buildTshirt(viewModel);
        tshirt.setId(viewModel.getId());

        tshirtRepo.save(tshirt);
    }

    @Transactional
    public void deleteTShirt(Integer id){
        tshirtRepo.deleteById(id);
    }

    public List<TshirtViewModel> findTShirtsByColor(String color){
        List<Tshirt> tshirtList = tshirtRepo.findByColor(color);
        List<TshirtViewModel> tshirtViewModelList = new ArrayList<>();

        //we iterate through the list of t-shirts to build the view models for the list
        for(Tshirt tshirt: tshirtList){
            TshirtViewModel tshirtViewModel = buildTshirtViewModel(tshirt);
            tshirtViewModelList.add(tshirtViewModel);
        }
        return tshirtViewModelList;
    }

    public List<TshirtViewModel> findTShirtsBySize(String size){
        List<Tshirt> tshirtList = tshirtRepo.findBySize(size);
        List<TshirtViewModel> tshirtViewModelList = new ArrayList<>();

        //we iterate through the list of t-shirts to build the view models for the list
        for(Tshirt tshirt: tshirtList){
            TshirtViewModel tshirtViewModel = buildTshirtViewModel(tshirt);
            tshirtViewModelList.add(tshirtViewModel);
        }
        return tshirtViewModelList;
    }

    //INVOICE API

//    @Transactional
//    public InvoiceViewModel saveInvoice(InvoiceViewModel viewModel){
//        Invoice invoice = buildInvoice(viewModel);
//        Optional<BigDecimal> processingFee = processingFeesRepo.findProcessingFeesByProductType(invoice.getItemType());
//        Optional<BigDecimal> salesTax = salesTaxRateRepo.findSalesTaxRateByState(invoice.getState());
//
//        //throw an exception here
//        if(invoice.getQuantity() < 0){
//
//        }
//        //check exceptions here to make sure the processing fee and sales tax are present first
//        invoice.setTax(salesTax.get());
//        invoice.setProcessingFee(processingFee.get());
//
//
//        BigDecimal total = BigDecimal.ZERO;
//        BigDecimal unitPrice = BigDecimal.ZERO;
//
//        //we go and check to see what item the invoice has
//        if(invoice.getItemType().equals("Game")){
//            Optional<Game> game = gameRepo.findById(invoice.getItemId());
//            unitPrice = game.isPresent() ? game.get().getPrice(): null;
//
//            //throw an error here requested quantity cannot be greater than what we have
//            if(invoice.getQuantity() > game.get().getQuantity()){
//
//            }
//        }
//        else if(invoice.getItemType().equals("T-Shirt")){
//                Optional<Tshirt> tshirt = tshirtRepo.findById(invoice.getItemId());
//                unitPrice = tshirt.isPresent() ? tshirt.get().getPrice(): null;
//
//            //throw an error here requested quantity cannot be greater than what we have
//            if(invoice.getQuantity() > tshirt.get().getQuantity()){
//
//            }
//        }
//        else if(invoice.getItemType().equals("Console")){
//                Optional<Console> console = consoleRepo.findById(invoice.getItemId());
//                unitPrice = console.isPresent() ? console.get().getPrice(): null;
//
//            //throw an error here requested quantity cannot be greater than what we have
//            if(invoice.getQuantity() > console.get().getQuantity()){
//
//            }
//        }
//        //throw an error here if the type is none of these
//
//
//        //throw another error here
//        if (unitPrice == null){
//
//        }
//        invoice.setUnitPrice(unitPrice);
//
//
//        //gets the subtotal by multiplying the unitprice by the quantity amount
//        total = total.add(unitPrice.multiply(BigDecimal.valueOf(invoice.getQuantity())));
//
//        //subtotal is the total before the sales tax and processing fee
//        invoice.setSubtotal(total);
//
//        //will calculate the sales tax check to make an exception here
//        salesTax = Optional.of(salesTax.get().multiply(total));
//        total = total.add(salesTax.get());
//
//        //exception for processing fee here
//        total = total.add(processingFee.get());
//
//        //checks to see if we should add on the over 10 processing fee
//        if(invoice.getQuantity() > 10){
//            total = total.add(BigDecimal.valueOf(15.49));
//        }
//
//        //sets the total
//        invoice.setTotal(total);
//
//        invoice = invoiceRepo.save(invoice);
//        viewModel.setTotal(invoice.getTotal());
//        viewModel.setProcessingFee(invoice.getProcessingFee());
//        viewModel.setTax(invoice.getTax());
//        viewModel.setUnitPrice(invoice.getUnitPrice());
//        viewModel.setSubtotal(invoice.getSubtotal());
//        viewModel.setId(invoice.getId());
//
//        return viewModel;
//
//    }


    public InvoiceViewModel findInvoice(Integer id){
        Optional<Invoice> invoice = invoiceRepo.findById(id);

        //if the invoice is present we send that if not we return null
        return invoice.isPresent() ? buildInvoiceViewModel(invoice.get()) : null;
    }

    public List<InvoiceViewModel> findAllInvoices(){
        List<Invoice> invoiceList = invoiceRepo.findAll();

        List<InvoiceViewModel> invoiceViewModelList = new ArrayList<>();

        //we iterate through the list of invoices to build the view models for the list
        for(Invoice invoice: invoiceList){
            InvoiceViewModel invoiceViewModel = buildInvoiceViewModel(invoice);
            invoiceViewModelList.add(invoiceViewModel);
        }

        return invoiceViewModelList;
    }

    @Transactional
    public void updateInvoice(InvoiceViewModel viewModel){
        Invoice invoice = buildInvoice(viewModel);
        invoice.setId(viewModel.getId());
        invoice.setProcessingFee(viewModel.getProcessingFee());
        invoice.setTax(viewModel.getTax());
        invoice.setUnitPrice(viewModel.getUnitPrice());
        invoice.setSubtotal(viewModel.getSubtotal());
        invoice.setTotal(viewModel.getTotal());

        invoiceRepo.save(invoice);
    }

    @Transactional
    public void removeInvoice(Integer id){
        invoiceRepo.deleteById(id);
    }

    public List<InvoiceViewModel> findInvoicesByName(String name){
        List<Invoice> invoiceList = invoiceRepo.findByName(name);
        List<InvoiceViewModel> invoiceViewModelList = new ArrayList<>();

        //we iterate through the list of invoices to build the view models for the list
        for(Invoice invoice: invoiceList){
            InvoiceViewModel invoiceViewModel = buildInvoiceViewModel(invoice);
            invoiceViewModelList.add(invoiceViewModel);
        }

        return invoiceViewModelList;
    }

}
