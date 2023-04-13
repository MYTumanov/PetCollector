package ru.petcollector.petcollector.model.user;

import java.io.Serializable;

import org.jetbrains.annotations.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterRequest implements Serializable {

    @NotNull
    private String password;

    @NotNull
    private String login;

    @NotNull
    private String phoneNumber;

}
