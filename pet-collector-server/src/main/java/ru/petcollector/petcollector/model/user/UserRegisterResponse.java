package ru.petcollector.petcollector.model.user;

import java.io.Serializable;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserRegisterResponse implements Serializable {

    @NotNull
    private Integer errCode;

    @Nullable
    private String errMessage;
    
}
