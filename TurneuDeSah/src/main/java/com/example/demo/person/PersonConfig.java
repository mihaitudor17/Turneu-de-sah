package com.example.demo.person;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class PersonConfig {
    @Bean
    CommandLineRunner commandLineRunner(PersonRepository repository) {
        return args -> {
            Person p1 = new Person(
                    "Blaga",
                    "Mihai",
                    "5010514080001",
                    LocalDate.of(2001, 05, 01),
                    "Barbat",
                    "0751515151",
                    true
            );
            Person p2 = new Person(
                    "Antal",
                    "Eduard",
                    "5010514080002",
                    LocalDate.of(2001, 9, 02),
                    "Barbat",
                    "0751515152",
                    true
            );
            repository.saveAll(
                    List.of(p1, p2)
            );
        };
    }
}
