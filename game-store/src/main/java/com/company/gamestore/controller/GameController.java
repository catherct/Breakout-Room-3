package com.company.gamestore.controller;

import com.company.gamestore.model.Game;
import com.company.gamestore.repository.GameControllerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class GameController {
    @Autowired
    private GameControllerRepository gameRepo;

    @PostMapping("/game")
    @ResponseStatus(HttpStatus.CREATED)
    public Game createGame(@RequestBody Game game){
        return gameRepo.save(game);
    }

    @GetMapping("/game/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Game getGameById(@PathVariable Integer id){
        Optional<Game> game = gameRepo.findById(id);

        if(game.isPresent()){
            return game.get();
        }

        return null;
    }

    @GetMapping("/game")
    @ResponseStatus(HttpStatus.OK)
    public List<Game> getAllGames(){
        return gameRepo.findAll();
    }

    @PutMapping("/game")
    @ResponseStatus(HttpStatus.OK)
    public void updateGame(@RequestBody Game game){
        Optional<Game> oldGame = gameRepo.findById(game.getId());
        if(oldGame.isPresent()){
            oldGame.get()
                    .setDescription(game.getDescription())
                    .setEsrbRating(game.getEsrbRating())
                    .setPrice(game.getPrice())
                    .setQuantity(game.getQuantity())
                    .setStudio(game.getStudio())
                    .setTitle(game.getTitle());
            gameRepo.save(oldGame.get());
        }
    }

    @DeleteMapping("/game")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGame(@PathVariable Integer id){
        gameRepo.deleteById(id);
    }

    @GetMapping("/game/studio/{studio}")
    @ResponseStatus(HttpStatus.OK)
    public List<Game> getGamesByStudio(@PathVariable String studio){
        return gameRepo.findGamesByStudio(studio);
    }

    @GetMapping("/game/esrb/{esrb}")
    @ResponseStatus(HttpStatus.OK)
    public List<Game> getGamesByEsrbRating(@PathVariable String esrb){
        return gameRepo.findGamesByEsrbRating(esrb);
    }

    @GetMapping("/game/title/{title}")
    @ResponseStatus(HttpStatus.OK)
    public Game getGameByTitle(@PathVariable String title){
        Optional<Game> game = gameRepo.findGameByTitle(title);
        if (game.isPresent()){
            return game.get();
        }

        return null;
    }



}
