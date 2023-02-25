package ru.petcollector.petcollector.model.user;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.petcollector.petcollector.model.AbstractModel;

@Getter
@Setter
@Document("users")
@NoArgsConstructor
public class User extends AbstractModel {

    @NotNull
    private String lastName;

    @NotNull
    private String firstName;

    @Nullable
    private String midleName;

    @NotNull
    private String phoneNumber;

    @NotNull
    @Indexed(unique = true)
    private String login;

    @Nullable
    @JsonIgnore
    private String password;

}
