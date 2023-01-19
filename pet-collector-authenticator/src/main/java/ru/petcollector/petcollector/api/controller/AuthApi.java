package ru.petcollector.petcollector.api.controller;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/oauth2")
public class AuthApi {

    @NotNull
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/authorized") 
    public String authorized() {
        return "authorized";
    }

}
