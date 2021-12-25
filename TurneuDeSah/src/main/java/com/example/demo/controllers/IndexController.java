package com.example.demo.controllers;

import com.example.demo.classes.Person;
import com.example.demo.classes.Tournament;
import com.example.demo.services.PersonService;
import com.example.demo.services.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
    @RequestMapping(value = "/savePerson", method=RequestMethod.POST)
    public String save(@ModelAttribute Person person, @RequestParam(value = "isActive", required = false)String checkboxValue){
        Person newPerson= new Person();
        if(checkboxValue != null)
        {
            newPerson.setActive(true);
        }
        else
        {
            newPerson.setActive(false);
        }
        newPerson.setName(person.getName());
        newPerson.setLastName(person.getLastName());
        newPerson.setCnp(person.getCnp());
        newPerson.setDateOfBirth(person.getDateOfBirth());
        //newPerson.setGender();
        newPerson.setPhoneNumber(person.getPhoneNumber());
        newPerson.setRank(person.getRank());
        //baga si user
        newPerson.getTournaments().add(tournamentService.getTournament(1L));
        tournamentService.getTournament(1L).getPersons().add(newPerson);
        personService.addNewPerson(newPerson);
        return "redirect:clasament";
    }
}
