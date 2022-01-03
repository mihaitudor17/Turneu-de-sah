package com.example.demo.services;

import com.example.demo.classes.Game;
import com.example.demo.classes.Tournament;
import com.example.demo.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class GameService {
    private final GameRepository gameRepository;

    @Autowired
    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<Game> getGamesByTournament(Tournament tournament){
        List<Game> games  = new ArrayList<>();
        gameRepository.findByTournament(tournament).
                forEach(games::add);
        System.out.println(games);
        return games;
    }

    public List<Game> getAllGames() {
        List<Game> games  = new ArrayList<>();
        gameRepository.findAll()
                .forEach(games::add);
        return games;
    }

}
