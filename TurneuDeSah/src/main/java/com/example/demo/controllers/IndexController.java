package com.example.demo.controllers;

import com.example.demo.classes.Person;
import com.example.demo.classes.User;
import com.example.demo.services.PersonService;
import com.example.demo.services.TournamentService;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Controller
public class IndexController {
    private TournamentService tournamentService;
    private PersonService personService;
    private UserService userService;
    public User currentUser = new User("Curent", "Utilizator", true);


    @Autowired
    public void TournamentController(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }

    @Autowired
    public void PersonController(PersonService personService) {
        this.personService = personService;
    }

    @Autowired
    public void UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("acasa", tournamentService.getFutureTournaments());
        return "acasa";
    }

    @RequestMapping(value = "/redirect", method = RequestMethod.GET)
    public String redirect() {
        return "redirect:turnee";
    }

     @RequestMapping(value = "/clasament")
    public String getAllPersons(Model model) {
         model.addAttribute("currentUser", currentUser);
        List<Person> persons = personService.getAllPersons();
        persons.sort((Person p1, Person p2)->p2.getRank()-p1.getRank());
        model.addAttribute("clasament", persons);
        return "clasament";
    }

    @RequestMapping("/turnee")
    public String turnee(Model model) {
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("turnee", tournamentService.getAllTournaments());
        return "turnee";
    }

    @GetMapping("/inscriere")
    public String inscriere(Model model) {
        model.addAttribute("currentUser", currentUser);
        return "inscriere";
    }

    @RequestMapping(value = "/savePerson", method = RequestMethod.POST)
    public String savePerson(@ModelAttribute Person person, @RequestParam(value = "isActive", required = false) String checkboxValue) {
        if(currentUser.getId()==null){
            return "redirect:trebuie_sa_fi_logat_mai_intai!";
        }
        Person newPerson = new Person();
        if (checkboxValue != null) {
            newPerson.setActive(true);
        } else {
            newPerson.setActive(false);
        }
        newPerson.setName(person.getName());
        newPerson.setLastName(person.getLastName());
        newPerson.setCnp(person.getCnp());
        newPerson.setDateOfBirth(person.getDateOfBirth());
        //newPerson.setGender();
        newPerson.setPhoneNumber(person.getPhoneNumber());
        newPerson.setRank(person.getRank());
        newPerson.setUser(currentUser);
        newPerson.getTournaments().add(tournamentService.getTournament(1L));
        tournamentService.getTournament(1L).getPersons().add(newPerson);
        personService.addNewPerson(newPerson);
        return "redirect:clasament";
    }

    @RequestMapping(value = "/loginUser", method = RequestMethod.POST)
    public String login(@ModelAttribute User user) {
        Optional<User> login = userService.getUserByUsernameAndPassword(user.getUsername(), user.getPassword());
        if (!login.isPresent()) {
            return "redirect:usersauparolaincorecta";
        }
        currentUser = login.get();
        return "redirect:";
    }

    @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
    public String save(@ModelAttribute User user) {
        if(userService.getUserByUsername(user.getUsername()).isPresent()){
            return "redirect:usernamealreadytaken";
        }
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(user.getPassword());
        newUser.setAdmin(false);
        userService.addNewUser(newUser);
        return "redirect:";
    }

}
