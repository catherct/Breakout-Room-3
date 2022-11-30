package com.company.gamestore.controller;

import com.company.gamestore.model.Tshirt;
import com.company.gamestore.repository.TshirtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TshirtController {

    @Autowired
    TshirtRepository shirtRepo;

    @PostMapping("/tshirt")
    @ResponseStatus(HttpStatus.CREATED)
    public Tshirt createTshirt(@RequestBody Tshirt shirt) {

        shirt.setId(shirt.getId());
        shirt.setSize(shirt.getSize());
        shirt.setColor(shirt.getColor());
        shirt.setDescription(shirt.getDescription());
        shirt.setPrice(shirt.getPrice());
        shirt.setQuantity(shirt.getQuantity());
        shirtRepo.save(shirt);

        return shirt;
    }

    @GetMapping("/tshirt/{id}")
    @ResponseStatus(HttpStatus.OK)
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

    @GetMapping("/tshirt")
    @ResponseStatus(HttpStatus.OK)
    public List<Tshirt> getAllShirts() {

        return shirtRepo.findAll();
    }

    @PutMapping("/tshirt/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateShirt(@PathVariable Integer id, @RequestBody Tshirt shirt) {

        List<Tshirt> shirts = shirtRepo.findAll();

        for (Tshirt favoriteShirt : shirts) {
            if (favoriteShirt.getId() == id) {
                shirts.set(favoriteShirt.getId(), shirt);
            }
        }
    }

    @DeleteMapping("/tshirt/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteShirt(Integer id) {

        List<Tshirt> shirts = shirtRepo.findAll();

        for (Tshirt leastFavoriteShirt : shirts) {
            if (leastFavoriteShirt.getId() == id) {
                shirts.remove(leastFavoriteShirt.getId()); // suspicious collections method calls
            }
        }
    }

    @GetMapping("/tshirt/{color}")
    @ResponseStatus(HttpStatus.OK)
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

    @GetMapping("/tshirt/{size}")
    @ResponseStatus(HttpStatus.OK)
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
