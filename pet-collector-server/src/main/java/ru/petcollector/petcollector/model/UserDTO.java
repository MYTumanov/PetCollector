package ru.petcollector.petcollector.model;

import org.jetbrains.annotations.Nullable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    @Nullable
    private String id;

    @Nullable
    private String lastName;

    @Nullable
    private String firstName;

    @Nullable
    private String midleName;

    @Nullable
    private String login;

    @Nullable
    private String password;
    
}
