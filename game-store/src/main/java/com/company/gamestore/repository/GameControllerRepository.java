package com.company.gamestore.repository;

import com.company.gamestore.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameControllerRepository extends JpaRepository<Game, Integer> {

    public List<Game> findGamesByStudio(String studio);
    public List<Game> findGamesByEsrbRating(String esrbRating);
    public Optional<Game> findGameByTitle(String title);
}
