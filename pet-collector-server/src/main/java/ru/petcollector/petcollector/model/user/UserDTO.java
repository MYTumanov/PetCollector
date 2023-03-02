package ru.petcollector.petcollector.model.user;

import java.util.Optional;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    private Optional<String> id = Optional.empty();

    private Optional<String> lastName = Optional.empty();

    private Optional<String> firstName = Optional.empty();

    private Optional<String> midleName = Optional.empty();

    private Optional<String> login = Optional.empty();

    private Optional<String> password = Optional.empty();

    private Optional<String> phoneNumber = Optional.empty();

    private Optional<Double> version = Optional.empty();

}
