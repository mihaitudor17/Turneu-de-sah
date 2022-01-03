package com.example.demo.repositories;

import com.example.demo.classes.Game;
import com.example.demo.classes.Person;
import com.example.demo.classes.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person,Long> {
    Optional<Person> findByCnp(@Param("cnp") String cnp);
}
