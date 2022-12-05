package com.company.gamestore.controller;

import com.company.gamestore.model.Console;
import com.company.gamestore.repository.ConsoleControllerRepository;
import com.company.gamestore.service.ServiceLayer;
import com.company.gamestore.viewmodel.ConsoleViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
public class ConsoleController {
    @Autowired
    private ServiceLayer serviceLayer;

    //Get all Consoles
    @GetMapping("/console")
    @ResponseStatus(HttpStatus.OK)
    public List<ConsoleViewModel> getConsoles() {
        return serviceLayer.findAllConsoles();
    }

    //Get Console by id
    @GetMapping("/console/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ConsoleViewModel getConsoleById(@PathVariable Integer id){
       ConsoleViewModel consoleViewModel =  serviceLayer.findConsole(id);
       if(consoleViewModel == null){
           throw new NullPointerException("Error: That Console does not exist");
       }
       return consoleViewModel;
    }
    //    Get Console by manufacturer
    @GetMapping("/console/manufacturer/{manufacturer}")
    @ResponseStatus(value = HttpStatus.OK)
    public List<ConsoleViewModel> getConsoleByManufacturer(@PathVariable String manufacturer) {
        return serviceLayer.findConsoleByManufacturer(manufacturer);
    }

    //Create Console
    @PostMapping("/console")
    @ResponseStatus(HttpStatus.CREATED)
    public ConsoleViewModel addConsole(@RequestBody ConsoleViewModel sample_console) {
        return serviceLayer.saveConsole(sample_console);
    }

    //update Console
    @PutMapping("/console")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateConsole(@RequestBody ConsoleViewModel sample_console) {
        serviceLayer.saveConsole(sample_console);
    }

    //delete Console
    @DeleteMapping("/console/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteConsole(@PathVariable int id) {
        serviceLayer.removeConsole(id);
    }
}
