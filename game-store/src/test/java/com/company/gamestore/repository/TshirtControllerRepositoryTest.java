package com.company.gamestore.repository;

import com.company.gamestore.model.Tshirt;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TshirtControllerRepositoryTest {

    @Autowired
    TshirtRepository tshirtRepository;

    @Before
    public void setUp() throws Exception {
        tshirtRepository.deleteAll();
    }

    @Test
    public void shouldCreateShirt() throws Exception {

        // arrange
        Tshirt shirt = new Tshirt();
        shirt.setId(1);
        shirt.setColor("blue");
        shirt.setSize("large");
        shirt.setDescription("large blue shirt");
        shirt.setPrice(BigDecimal.valueOf(13.99));
        shirt.setQuantity(2);

        // act
        shirt = tshirtRepository.save(shirt);

        // assert
        Optional<Tshirt> shirt1 = tshirtRepository.findById(shirt.getId());
        assertTrue(shirt1.isPresent());

    }


    @Test
    public void shouldFindShirtsById() {

        // arrange
        Tshirt shirt = new Tshirt();
        shirt.setId(1);
        shirt.setColor("blue");
        shirt.setSize("large");
        shirt.setDescription("large blue shirt");
        shirt.setPrice(BigDecimal.valueOf(13.99));
        shirt.setQuantity(2);

        // act
        shirt =tshirtRepository.save(shirt);

        // assert
        Optional<Tshirt> shirt1 = tshirtRepository.findById(shirt.getId());
        assertEquals(shirt1.get(), shirt);

        shirt1 = tshirtRepository.findById(shirt.getId());

        assertTrue(shirt1.isPresent());

    }


    @Test
    public void shouldFindAllShirts() {

        // arrange
        Tshirt shirt1 = new Tshirt();
        shirt1.setId(1);
        shirt1.setColor("blue");
        shirt1.setSize("large");
        shirt1.setDescription("large blue shirt");
        shirt1.setPrice(BigDecimal.valueOf(13.99));
        shirt1.setQuantity(2);

        Tshirt shirt2 = new Tshirt();
        shirt2.setId(4);
        shirt2.setColor("red");
        shirt2.setSize("large");
        shirt2.setDescription("large red shirt");
        shirt2.setPrice(BigDecimal.valueOf(13.99));
        shirt2.setQuantity(2);

        // act
        tshirtRepository.save(shirt1);
        tshirtRepository.save(shirt2);

        // assert
        List<Tshirt> shirtList = tshirtRepository.findAll();
        assertFalse(shirtList.isEmpty());

    }

    @Test
    public void shouldFindShirtsByColor() {

        // arrange
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

        // act
        tshirtRepository.save(shirt1);
        tshirtRepository.save(shirt2);

        // assert
        List<Tshirt> blueShirts = tshirtRepository.findByColor("blue");
        assertFalse(blueShirts.isEmpty());

    }

    @Test
    public void shouldFindAllShirtsBySize() {

        // arrange
        Tshirt shirt1 = new Tshirt();
        shirt1.setId(1);
        shirt1.setColor("blue");
        shirt1.setSize("large");
        shirt1.setDescription("large blue shirt");
        shirt1.setPrice(BigDecimal.valueOf(13.99));
        shirt1.setQuantity(2);

        Tshirt shirt2 = new Tshirt();
        shirt2.setId(5);
        shirt2.setColor("red");
        shirt2.setSize("large");
        shirt2.setDescription("large red shirt");
        shirt2.setPrice(BigDecimal.valueOf(13.99));
        shirt2.setQuantity(2);

        // act
        tshirtRepository.save(shirt1);
        tshirtRepository.save(shirt2);

        // assert
        List<Tshirt> largeShirts = tshirtRepository.findBySize("large");
        assertFalse(largeShirts.isEmpty());

    }

    @Test
    public void shouldUpdateShirt() {

        // arrange
        Tshirt shirt = new Tshirt();
        shirt.setId(1);
        shirt.setColor("blue");
        shirt.setSize("large");
        shirt.setDescription("large blue shirt");
        shirt.setPrice(BigDecimal.valueOf(13.99));
        shirt.setQuantity(2);

        // act
        shirt = tshirtRepository.save(shirt);

        // assert
        Optional<Tshirt> shirt1 = tshirtRepository.findById(shirt.getId());
        assertEquals(shirt1.get(), shirt);

        // arrange
        shirt.setQuantity(4);

        // act
        shirt = tshirtRepository.save(shirt);

        // assert
        Optional<Tshirt> shirt2 = tshirtRepository.findById(shirt.getId());
        assertEquals(shirt2.get(), shirt);

        assertTrue(shirt2.isPresent());

    }

    @Test
    public void shouldDeleteShirt() {

        // arrange
        Tshirt shirt = new Tshirt();
        shirt.setId(1);
        shirt.setColor("blue");
        shirt.setSize("large");
        shirt.setDescription("large blue shirt");
        shirt.setPrice(BigDecimal.valueOf(13.99));
        shirt.setQuantity(2);

        // act
        shirt = tshirtRepository.save(shirt);

        // assert
        Optional<Tshirt> shirt1 = tshirtRepository.findById(shirt.getId());
        assertEquals(shirt1.get(), shirt);

        tshirtRepository.deleteById(shirt.getId());

        shirt1 = tshirtRepository.findById(shirt.getId());

        assertFalse(shirt1.isPresent());

    }
}