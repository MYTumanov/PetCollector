package ru.petcollector.petcollector.model;

import org.jetbrains.annotations.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {

    @NotNull
    private String login;

    @NotNull
    private String password;

}
