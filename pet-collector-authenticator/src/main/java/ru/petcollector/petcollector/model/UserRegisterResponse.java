package ru.petcollector.petcollector.model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterResponse {

    @NotNull
    private Integer errCode;

    @Nullable
    private String errMessage;
    
}
