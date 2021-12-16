package com.example.demo.controllers;

import com.example.demo.classes.Person;
import com.example.demo.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.List;

@Controller
public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

//    @GetMapping
//    public List<Person> getPersons() {
//        return personService.getPersons();
//    }
//
//    @PostMapping
//    public void registerNewPerson(@RequestBody Person person) {
//        personService.addNewPerson(person);
//    }

    @RequestMapping(value = "/clasament")
    public String getAllPersons(Model model) {
        List<Person> persons = personService.getAllPersons();
        persons.sort((Person p1, Person p2)->p2.getRank()-p1.getRank());
        model.addAttribute("clasament", persons);
        return "clasament";
    }

    @RequestMapping(value = "/saveUser", method=RequestMethod.POST)
    public String save(@ModelAttribute Person person){
        Person newPerson= new Person("", "", "", LocalDate.now(),"", "",0,false);
        newPerson.setName(person.getName());
        personService.addNewPerson(newPerson);
        System.out.println("Lmao");
        return "redirect:clasament";
    }
//    @GetMapping("/clasament/{id}")
//    public String getPerson(@PathVariable Long id) {
//        return personService.getPerson(id).toString();
//    }

}
