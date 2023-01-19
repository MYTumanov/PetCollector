package ru.petcollector.petcollector.model;

import org.jetbrains.annotations.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    
    @NotNull
    private String username;

    @NotNull
    private String password;
    
}
