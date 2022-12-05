package com.company.gamestore.controller;

import com.company.gamestore.model.Game;
import com.company.gamestore.repository.GameControllerRepository;
import com.company.gamestore.service.ServiceLayer;
import com.company.gamestore.viewmodel.GameViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class GameController {

    @Autowired
    private ServiceLayer serviceLayer;

    @PostMapping("/game")
    @ResponseStatus(HttpStatus.CREATED)
    public GameViewModel createGame(@RequestBody GameViewModel game){
        return serviceLayer.saveGame(game);
    }

    @GetMapping("/game/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GameViewModel getGameById(@PathVariable Integer id){
        GameViewModel gameViewModel = serviceLayer.findGame(id);
        if(gameViewModel == null){
            throw new NullPointerException("Error: That game does not exist.");
        }

        return gameViewModel;
    }

    @GetMapping("/game")
    @ResponseStatus(HttpStatus.OK)
    public List<GameViewModel> getAllGames(){
        return serviceLayer.findAllGames();
    }

    @PutMapping("/game")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateGame(@RequestBody GameViewModel game){
        serviceLayer.updateGame(game);
    }

    @DeleteMapping("/game/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGame(@PathVariable Integer id){
        serviceLayer.deleteGame(id);
    }

    @GetMapping("/game/studio/{studio}")
    @ResponseStatus(HttpStatus.OK)
    public List<GameViewModel> getGamesByStudio(@PathVariable String studio){
        return serviceLayer.findGamesByStudio(studio);
    }

    @GetMapping("/game/esrb/{esrb}")
    @ResponseStatus(HttpStatus.OK)
    public List<GameViewModel> getGamesByEsrbRating(@PathVariable String esrb){
        return serviceLayer.findGamesByEsrbRating(esrb);
    }

    @GetMapping("/game/title/{title}")
    @ResponseStatus(HttpStatus.OK)
    public List<GameViewModel> getGameByTitle(@PathVariable String title){
        return serviceLayer.findGamesByTitle(title);
    }



}
