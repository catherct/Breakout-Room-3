package com.company.gamestore.controller;

import com.company.gamestore.model.Tshirt;
import com.company.gamestore.repository.TshirtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class TshirtController {

    @Autowired
    TshirtRepository shirtRepo;

    @RequestMapping(value = "/tshirt", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Tshirt createTshirt(@RequestBody @Valid Tshirt shirt) {

        shirt.setId(shirt.getId());
        shirt.setSize(shirt.getSize());
        shirt.setColor(shirt.getColor());
        shirt.setDescription(shirt.getDescription());
        shirt.setPrice(shirt.getPrice());
        shirt.setQuantity(shirt.getQuantity());
        shirtRepo.save(shirt);

        return shirt;
    }

    @RequestMapping(value = "/tshirt/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Tshirt getShirt(@PathVariable Integer id) {

        List<Tshirt> shirts = shirtRepo.findAll();

        Tshirt shirt = null;

        for (Tshirt favoriteShirt : shirts) {
            if (favoriteShirt.getId() == id) {
                shirt = favoriteShirt;
            }
        }
        return shirt;
    }

    @RequestMapping(value = "/tshirt", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Tshirt> getAllShirts() {

        return shirtRepo.findAll();
    }

    @RequestMapping(value = "/tshirt/{id}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateShirt(@PathVariable Integer id, @RequestBody Tshirt shirt) {

        List<Tshirt> shirts = shirtRepo.findAll();

        for (Tshirt favoriteShirt : shirts) {
            if (favoriteShirt.getId() == id) {
                shirts.set(favoriteShirt.getId(), shirt);
            }
        }
    }

    @RequestMapping(value = "/tshirt/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteShirt(Integer id) {

        List<Tshirt> shirts = shirtRepo.findAll();

        for (Tshirt leastFavoriteShirt : shirts) {
            if (leastFavoriteShirt.getId() == id) {
                shirts.remove(leastFavoriteShirt.getId()); // suspicious collections method calls
            }
        }
    }

    @RequestMapping(value = "/tshirt/{color}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Tshirt findByColor(@PathVariable String color) {

        List<Tshirt> shirts = shirtRepo.findAll();

        Tshirt shirt = null;

        for (Tshirt favoriteShirt : shirts) {
            if (favoriteShirt.getColor() == color) {
                shirt = favoriteShirt;
            }
        }
        return shirt;
    }

    @RequestMapping(value = "/tshirt/{size}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Tshirt findBySize(@PathVariable String size) {

        List<Tshirt> shirts = shirtRepo.findAll();

        Tshirt shirt = null;

        for (Tshirt favoriteShirt : shirts) {
            if (favoriteShirt.getSize() == size) {
                shirt = favoriteShirt;
            }
        }
        return shirt;
    }
}
