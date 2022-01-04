package com.example.demo.repositories;

import com.example.demo.classes.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TournamentRepository extends JpaRepository<Tournament,Long> {
    List<Tournament> findByDateGreaterThanEqual(@Param("date") LocalDate date);
    //Long deleteById(@Param("id") Long id);
}
