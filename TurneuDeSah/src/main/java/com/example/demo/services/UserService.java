package com.example.demo.services;

import com.example.demo.classes.User;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public void addNewUser(User user){
        userRepository.save(user);
    }
    public Optional<User> getUserByUsernameAndPassword(String username, String password){
        return this.userRepository.findByUsernameAndPassword(username,password);
    }
    public Optional<User> getUserByUsername(String username){
        return this.userRepository.findByUsername(username);
    }
}
