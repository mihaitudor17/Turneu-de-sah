package com.example.demo.controllers;

import com.example.demo.services.PersonService;
import com.example.demo.services.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {
    private TournamentService tournamentService;
    private PersonService personService;

    @Autowired
    public void TournamentController(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }

    @Autowired
    public void PersonController(PersonService personService) {
        this.personService = personService;
    }

    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("acasa",tournamentService.getFutureTournaments());
        model.addAttribute("persoane",personService.getAllPersons());
        return "acasa";
    }
    @RequestMapping(value = "/redirect", method = RequestMethod.GET)
    public String redirect() {
        return "redirect:turnee";
    }

//    @GetMapping("/clasament")
//    public String clasament() {
//        return "clasament";
//    }

    @RequestMapping("/turnee")
    public String turnee(Model model) {
        model.addAttribute("turnee",tournamentService.getAllTournaments());
        model.addAttribute("persoane",personService.getAllPersons());
        return "turnee";
    }
    @GetMapping("/inscriere")
    public String inscriere() {
        return "inscriere";
    }
    @GetMapping("/saveUser")
    public String saveUser() {
        return "redirect:clasament";
    }
}
