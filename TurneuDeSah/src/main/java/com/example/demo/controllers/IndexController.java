package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {
//    @RequestMapping("/")
//    public String index(){
//        return "acasa";
//    }
    @RequestMapping(value = "/redirect", method = RequestMethod.GET)
    public String redirect() {
        return "redirect:turnee";
    }

//    @GetMapping("/clasament")
//    public String clasament() {
//        return "clasament";
//    }
//    @GetMapping("/turnee")
//    public String turnee() {
//        return "turnee";
//    }
    @GetMapping("/adaugare")
    public String adaugare() {
        return "adaugare";
    }
    @GetMapping("/inscriere")
    public String inscriere() {
        return "inscriere";
    }
    @GetMapping("/saveUser")
    public String saveUser() {
        return "redirect:clasament";
    }
}