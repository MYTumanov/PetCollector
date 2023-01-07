package ru.petcollector.petcollector.model;

import org.jetbrains.annotations.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginResponse {

    @NotNull
    private String token;

    @NotNull
    private static final String TOKEN_TYPE = "Bearer";

    public LoginResponse(@NotNull final String token) {
        this.token = token;
    }

}
