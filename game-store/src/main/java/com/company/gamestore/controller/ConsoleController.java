package com.company.gamestore.controller;

import com.company.gamestore.model.Console;
import com.company.gamestore.repository.ConsoleControllerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
public class ConsoleController {
    @Autowired
    ConsoleControllerRepository repo;

    //Get all Consoles
    @GetMapping("/console")
    public List<Console> getConsoles() {
        return repo.findAll();
    }

    //Get Console by id
    @GetMapping("/console/{id}")
    public Console getConsoleById(@PathVariable int id) {

        Optional<Console> returnVal = repo.findById(id);
        if (returnVal.isPresent()) {
            return returnVal.get();
        } else {
            return null;
        }
    }
    //    Get Console by manufacturer
    @GetMapping("/console/manufacturer/{manufacturer}")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Console> getConsoleByManufacturer(@PathVariable String manufacturer) {

        List<Console> returnVal = repo.findConsoleByManufacturer(manufacturer);
        return repo.findConsoleByManufacturer(manufacturer);
    }

    //Create Console
    @PostMapping("/console")
    @ResponseStatus(HttpStatus.CREATED)
    public Console addConsole(@RequestBody Console sample_console) {
        //weird behaviour .. dunno why
        return (Console) repo.save(sample_console);
    }

    //update Console
    @PutMapping("/console")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateConsole(@RequestBody Console sample_console) {
        repo.save(sample_console);
    }

    //delete Console
    @DeleteMapping("/console/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteConsole(@PathVariable int id) {
        repo.deleteById(id);
    }
}
