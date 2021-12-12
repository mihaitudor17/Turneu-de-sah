package com.example.demo.services;

import com.example.demo.classes.Person;
import com.example.demo.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getPersons() {
        return personRepository.findAll();
    }

    public void addNewPerson(Person person) {
        personRepository.save(person);
    }

    public List<Person> getAllPersons() {
        List<Person> persons  = new ArrayList<>();
        personRepository.findAll()
                .forEach(persons::add);
        return persons;
    }
    public Person getPerson(Long id){
        return personRepository.findById(id).get();
    }
}
