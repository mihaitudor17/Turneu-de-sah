package com.example.demo.controllers;

import com.example.demo.classes.Person;
import com.example.demo.classes.User;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

//    @RequestMapping(value = "/loginUser", method = RequestMethod.POST)
//    public String login(@ModelAttribute User user) {
//        Optional<User> login = userService.getUserByUsernameAndPassword(user.getUsername(), user.getPassword());
//        if(!login.isPresent()){
//            return "redirect:usersauparolaincorecta";
//        }
//        System.out.println(login.get().toString());
//        return "redirect:";
//    }

//    @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
//    public String save(@ModelAttribute User user) {
//        System.out.println(user.toString());
//        User newUser = new User();
//        System.out.println(newUser.toString());
//        newUser.setUsername(user.getUsername());
//        newUser.setPassword(user.getPassword());
//        newUser.setAdmin(false);
//        userService.addNewUser(newUser);
//        return "redirect:";
//    }
}
