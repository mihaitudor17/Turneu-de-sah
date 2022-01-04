package com.example.demo.services;

import com.example.demo.classes.Person;
import com.example.demo.classes.Tournament;
import com.example.demo.repositories.PersonRepository;
import com.example.demo.repositories.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TournamentService {
    private final TournamentRepository tournamentRepository;

    @Autowired
    public TournamentService(TournamentRepository tournamentRepository) {
        this.tournamentRepository = tournamentRepository;
    }
    public List<Tournament> getTournaments() {
        return tournamentRepository.findAll();
    }

    public void addNewTournament(Tournament tournament) {
        tournamentRepository.save(tournament);
    }

    public List<Tournament> getAllTournaments() {
        List<Tournament> tournaments  = new ArrayList<>();
        tournamentRepository.findAll()
                .forEach(tournaments::add);
        return tournaments;
    }

    public List<Tournament> getFutureTournaments(){
        List<Tournament> tournaments  = new ArrayList<>();
        tournamentRepository.findByDateGreaterThanEqual(LocalDate.now()).
                forEach(tournaments::add);
        return tournaments;
    }
    public Tournament getTournament(Long id){
        return tournamentRepository.findById(id).get();
    }

    public void deleteByName(Long id){
        tournamentRepository.deleteById(id);
    }
}
