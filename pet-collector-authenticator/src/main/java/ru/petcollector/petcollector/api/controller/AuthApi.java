package ru.petcollector.petcollector.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/oauth2")
public class AuthApi {

    @GetMapping("/authorized") 
    public String authorized() {
        return "authorized";
    }

}
