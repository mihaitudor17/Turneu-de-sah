package com.example.demo.controllers;

import com.example.demo.classes.Person;
import com.example.demo.classes.Tournament;
import com.example.demo.services.PersonService;
import com.example.demo.services.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class TournamentController {
    private final TournamentService tournamentService;

    @Autowired
    public TournamentController(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }

    @GetMapping
    public List<Tournament> getTournaments() {
        return tournamentService.getTournaments();
    }

    @PostMapping
    public void registerNewTournament(@RequestBody Tournament tournament) {
        tournamentService.addNewTournament(tournament);
    }

    @RequestMapping(value = "/turnee")
    public String getAllTournaments(Model model) {
        List<Tournament> tournaments = tournamentService.getAllTournaments();
        model.addAttribute("turnee", tournaments);
        return "turnee";
    }
}
