package com.example.demo.repositories;

import com.example.demo.classes.Person;
import com.example.demo.classes.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
