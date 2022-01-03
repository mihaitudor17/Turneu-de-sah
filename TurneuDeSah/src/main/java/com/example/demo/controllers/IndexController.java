package com.example.demo.controllers;

import com.example.demo.classes.Game;
import com.example.demo.classes.Person;
import com.example.demo.classes.Tournament;
import com.example.demo.classes.User;
import com.example.demo.services.GameService;
import com.example.demo.services.PersonService;
import com.example.demo.services.TournamentService;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Controller
public class IndexController {
    private TournamentService tournamentService;
    private PersonService personService;
    private UserService userService;
    public User currentUser = new User("Curent", "Utilizator", true);
    private GameService gameService;

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

    @Autowired
    public void GameController(GameService gameService) {
        this.gameService = gameService;
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
        model.addAttribute("partide", gameService.getAllGames());
        return "turnee";
    }

    @GetMapping("/inscriere")
    public String inscriere(Model model) {
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("futureTournaments", tournamentService.getFutureTournaments());
        return "inscriere";
    }

    @RequestMapping(value = "/savePerson", method = RequestMethod.POST)
    public String savePerson(@ModelAttribute Person person,
                             @RequestParam(value = "isActive", required = false) String checkboxValue,
                             @RequestParam(value = "selectedTournament", required = false) Long selectedTournament) {
        if(currentUser.getId()==null){
            return "redirect:trebuie_sa_fi_logat_mai_intai!";
        }
        if(personService.getPersonByCnp(person.getCnp()).isPresent()){
            return "redirect:cnp_deja_inregistrat!";
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
        newPerson.setGender(person.getGender());
        newPerson.setPhoneNumber(person.getPhoneNumber());
        newPerson.setRank(person.getRank());
        newPerson.setUser(currentUser);
        newPerson.getTournaments().add(tournamentService.getTournament(selectedTournament));
        tournamentService.getTournament(selectedTournament).getPersons().add(newPerson);
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
            return "redirect:";
        }
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(user.getPassword());
        newUser.setAdmin(false);
        userService.addNewUser(newUser);
        return "redirect:";
    }
    @RequestMapping(value = "/resetCurrentUser", method = RequestMethod.POST)
    public String resetCurrentUser() {
        currentUser.setId(null);
        currentUser.setUsername("");
        currentUser.setPassword("");
        currentUser.setAdmin(false);
        return "redirect:";
    }

    @RequestMapping(value = "/saveGame", method = RequestMethod.POST)
    public String saveGame(@RequestParam(value = "cnpWhite", required = true) String cnpWhite,
                           @RequestParam(value = "cnpBlack", required = true) String cnpBlack,
                           @RequestParam(value = "cnpWinner", required = false) String cnpWinner,
                           @RequestParam(value = "turneu", required = false) Long idTurneu){
        System.out.println("Das what i get\n\n\n\n");
        System.out.println(idTurneu);
        if(!personService.getPersonByCnp(cnpWhite).isPresent()&&
                !personService.getPersonByCnp(cnpBlack).isPresent()
        ){
            return "redirect:cnpGresit";
        }
        Game game= new Game(personService.getPersonByCnp(cnpWhite).get(),
                personService.getPersonByCnp(cnpBlack).get(),
                personService.getPersonByCnp(cnpWinner).get(),
                tournamentService.getTournament(idTurneu)
                );

        gameService.addNewGame(game);
        return "redirect:turnee";
    }
    @Transactional
    @RequestMapping(value = "/updateWinner", method = RequestMethod.POST)
    public String updateWinner(@RequestParam(value = "color", required = false) String color,
                               @RequestParam(value = "game", required = true) Long gameId){
        System.out.println("\n\n\n\n\n\n\n");
        System.out.println(color);
        System.out.println(gameId);
        System.out.println("Cringe");
        if(Objects.equals(color, "black")){
            System.out.println("Got to black");
            gameService.getGameById(gameId).get().setWinner(
                    gameService.getGameById(gameId).get().getBlack());
        }else if(Objects.equals(color, "white")){
            System.out.println("GOt to white");
            gameService.getGameById(gameId).get().setWinner(
                    gameService.getGameById(gameId).get().getWhite());
        }
        System.out.println("Cringe");
        return "redirect:turnee";
    }

    @RequestMapping(value="/saveTournament",method = RequestMethod.POST)
    public String saveTournament(@ModelAttribute Tournament tournament){
        System.out.println("Cringe");
        Tournament newTournament=new Tournament(
                tournament.getName(),
                tournament.getDate(),
                tournament.getTime(),
                tournament.getPrizeMoney(),
                tournament.getPhoneNumber(),
                true
        );
        tournamentService.addNewTournament(newTournament);
        return "redirect:turnee";
    }
}
