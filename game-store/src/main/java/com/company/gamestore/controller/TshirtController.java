package com.company.gamestore.controller;

import com.company.gamestore.service.ServiceLayer;
import com.company.gamestore.viewmodel.TshirtViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TshirtController {

    @Autowired
    private ServiceLayer serviceLayer;

    // create new shirt
    @PostMapping("/tshirt")
    @ResponseStatus(HttpStatus.CREATED)
    public TshirtViewModel createTshirt(@RequestBody TshirtViewModel shirt) {

        return serviceLayer.saveTShirt(shirt);
    }

    // retrieve shirt by id
    @GetMapping("/tshirt/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TshirtViewModel getShirt(@PathVariable Integer id) {

        TshirtViewModel targetShirt = serviceLayer.findTShirt(id);

        if (targetShirt == null) {
            throw new IllegalArgumentException("Error: Entry does not exist.");
        }
        return targetShirt;
    }

    // retrieve all shirts
    @GetMapping("/tshirt")
    @ResponseStatus(HttpStatus.OK)
    public List<TshirtViewModel> getAllShirts() {

        return serviceLayer.findAllTShirts();
    }

    // update existing shirt
    @PutMapping("/tshirt/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateShirt(@PathVariable Integer id, @RequestBody TshirtViewModel shirt) {

        TshirtViewModel targetShirt = serviceLayer.findTShirt(id);

        serviceLayer.updateTShirt(targetShirt);
    }

    // delete existing shirt
    @DeleteMapping("/tshirt/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteShirt(@PathVariable Integer id) {

        TshirtViewModel targetShirt = serviceLayer.findTShirt(id);

        serviceLayer.deleteTShirt(id);
    }

    // retrieve shirt by color
    @GetMapping("/tshirt/{color}")
    @ResponseStatus(HttpStatus.OK)
    public List<TshirtViewModel> findByColor(@PathVariable String color) {

        return serviceLayer.findTShirtsByColor(color);
    }

    // retrieve shirt by size
    @GetMapping("/tshirt/{size}")
    @ResponseStatus(HttpStatus.OK)
    public List<TshirtViewModel> findBySize(@PathVariable String size) {

        return serviceLayer.findTShirtsBySize(size);
    }
}
