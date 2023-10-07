package ru.petcollector.petcollector.model.user;

import org.jetbrains.annotations.Nullable;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Document("users")
@NoArgsConstructor
public class User extends AbstractTelegramUser {

    @Nullable
    private String lastName;

    @Nullable
    private String firstName;

    @Nullable
    private String midleName;

    @Nullable
    private String phoneNumber;

    @Nullable
    private String login;

    @Nullable
    @JsonIgnore
    private String password;

}
