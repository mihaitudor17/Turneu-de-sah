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
    public User currentUser = new User("Curent", "Utilizator", false);
    private GameService gameService;
    private String errorType = "";

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
        model.addAttribute("errorType", errorType);
        errorType = "";
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
        persons.sort((Person p1, Person p2) -> p2.getRank() - p1.getRank());
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
        if (currentUser.getId() == null) {
            errorType = "youMustBeLoggedInFirst";
            return "redirect:";
        }
        if (personService.getPersonByCnp(person.getCnp()).isPresent()) {
            errorType = "cnpAlreadyExistent";
            return "redirect:";
        }
        Person newPerson = new Person();
        newPerson.setActive(checkboxValue != null);
        newPerson.setName(person.getName());
        newPerson.setLastName(person.getLastName());
        newPerson.setCnp(person.getCnp());
        newPerson.setDateOfBirth(person.getDateOfBirth());
        newPerson.setGender(person.getGender());
        newPerson.setPhoneNumber(person.getPhoneNumber());
        newPerson.setRank(person.getRank());
        newPerson.setUser(currentUser);
        newPerson.getTournaments().add(tournamentService.getTournament(selectedTournament).get());
        tournamentService.getTournament(selectedTournament).get().getPersons().add(newPerson);
        personService.addNewPerson(newPerson);
        errorType = "";
        return "redirect:clasament";
    }

    @RequestMapping(value = "/loginUser", method = RequestMethod.POST)
    public String login(@ModelAttribute User user) {
        Optional<User> login = userService.getUserByUsernameAndPassword(user.getUsername(), user.getPassword());
        if (!login.isPresent()) {
            errorType = "usernameAndPasswordDontMatch";
            return "redirect:";
        }
        currentUser = login.get();
        errorType = "";
        return "redirect:";
    }

    @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
    public String save(@ModelAttribute User user) {
        if (userService.getUserByUsername(user.getUsername()).isPresent()) {
            errorType = "usernameAlreadyTaken";
            return "redirect:";
        }
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(user.getPassword());
        newUser.setAdmin(false);
        userService.addNewUser(newUser);
        errorType = "";
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

    @Transactional
    @RequestMapping(value = "/saveGame", method = RequestMethod.POST)
    public String saveGame(@RequestParam(value = "cnpWhite", required = true) String cnpWhite,
                           @RequestParam(value = "cnpBlack", required = true) String cnpBlack,
                           @RequestParam(value = "cnpWinner", required = false) String cnpWinner,
                           @RequestParam(value = "turneu", required = false) Long idTurneu) {
        if (!personService.getPersonByCnp(cnpWhite).isPresent() &&
                !personService.getPersonByCnp(cnpBlack).isPresent()
        ) {
            errorType = "wrongCnp";
            return "redirect:";
        }
        if (personService.getPersonByCnp(cnpWinner).isPresent()) {
            Game game = new Game(personService.getPersonByCnp(cnpWhite).get(),
                    personService.getPersonByCnp(cnpBlack).get(),
                    personService.getPersonByCnp(cnpWinner).get(),
                    tournamentService.getTournament(idTurneu).get());
            gameService.addNewGame(game);

            if(Objects.equals(cnpWinner, cnpBlack)){
                int rankBlack = personService.getPerson(game.getBlack().getId()).getRank();
                personService.getPerson(game.getBlack().getId()).setRank(rankBlack + 15);

                int rankWhite = personService.getPerson(game.getWhite().getId()).getRank();
                personService.getPerson(game.getWhite().getId()).setRank(rankWhite - 15);
            }else{

                int rankBlack = personService.getPerson(game.getBlack().getId()).getRank();
                personService.getPerson(game.getBlack().getId()).setRank(rankBlack - 15);

                int rankWhite = personService.getPerson(game.getWhite().getId()).getRank();
                personService.getPerson(game.getWhite().getId()).setRank(rankWhite + 15);
            }
        } else {
            Game game = new Game(personService.getPersonByCnp(cnpWhite).get(),
                    personService.getPersonByCnp(cnpBlack).get(),
                    null,
                    tournamentService.getTournament(idTurneu).get());
            gameService.addNewGame(game);
        }
        errorType = "";
        return "redirect:turnee";
    }

    @Transactional
    @RequestMapping(value = "/updateWinner", method = RequestMethod.POST)
    public String updateWinner(@RequestParam(value = "color", required = false) String color,
                               @RequestParam(value = "game", required = true) Long gameId) {
        if (Objects.equals(color, "negru")) {
            gameService.getGameById(gameId).get().setWinner(
                    gameService.getGameById(gameId).get().getBlack());

            int rankBlack = personService.getPerson(
                    gameService.getGameById(gameId).get().getBlack().getId()).getRank();
            personService.getPerson(
                    gameService.getGameById(gameId).get().getBlack().getId()).setRank(rankBlack + 15);

            int rankWhite = personService.getPerson(
                    gameService.getGameById(gameId).get().getWhite().getId()).getRank();
            personService.getPerson(
                    gameService.getGameById(gameId).get().getWhite().getId()).setRank(rankWhite - 15);
        } else if (Objects.equals(color, "alb")) {
            gameService.getGameById(gameId).get().setWinner(
                    gameService.getGameById(gameId).get().getWhite());

            int rankBlack = personService.getPerson(
                    gameService.getGameById(gameId).get().getBlack().getId()).getRank();
            personService.getPerson(
                    gameService.getGameById(gameId).get().getBlack().getId()).setRank(rankBlack - 15);

            int rankWhite = personService.getPerson(
                    gameService.getGameById(gameId).get().getWhite().getId()).getRank();
            personService.getPerson(
                    gameService.getGameById(gameId).get().getWhite().getId()).setRank(rankWhite + 15);
        }
        return "redirect:turnee";
    }

    @RequestMapping(value = "/saveTournament", method = RequestMethod.POST)
    public String saveTournament(@ModelAttribute Tournament tournament) {
        Tournament newTournament = new Tournament(
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

    @Transactional
    @RequestMapping(value = "/deleteTournament", method = RequestMethod.POST)
    public String deleteTournament(@RequestParam(value = "delete", required = true) Long id) {
        if (!tournamentService.getTournament(id).isPresent()) {
            errorType = "unexistingTournament";
            return "redirect:";
        }
        while (!gameService.getGamesByTournament(tournamentService.getTournament(id).get()).isEmpty()) {
            gameService.deleteByTournament(tournamentService.getTournament(id).get());
        }

        tournamentService.deleteById(id);
        errorType = "";
        return "redirect:turnee";
    }
}
