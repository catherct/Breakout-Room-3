package com.company.gamestore.service;

import com.company.gamestore.model.Tshirt;
import com.company.gamestore.repository.*;
import com.company.gamestore.viewmodel.TshirtViewModel;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public void setUp() throws Exception {

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
        assertEquals(tshirtViewModel, toCompare);

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

}