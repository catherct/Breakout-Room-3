package com.company.gamestore.repository;

import com.company.gamestore.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

    @Repository
    public interface GameControllerRepository extends JpaRepository<Game, Integer> {

        public List<Game> findGamesByStudio(String studio);
        public List<Game> findGamesByEsrbRating(String esrbRating);
        public List<Game> findGamesByTitle(String title);
    }
