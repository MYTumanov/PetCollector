package ru.petcollector.petcollector.map;

import org.jetbrains.annotations.NotNull;

import ru.petcollector.petcollector.model.user.User;
import ru.petcollector.petcollector.model.user.UserDTO;

public interface UserMapper {

    public static User map(@NotNull final UserDTO userDTO) {
        userDTO.setId(null);
        return map(userDTO, new User());
    }

    public static User map(@NotNull final UserDTO userDTO, @NotNull final User user) {
        user.setFirstName(userDTO.getFirstName() != null ? userDTO.getFirstName() : user.getFirstName());
        user.setLastName(userDTO.getLastName() != null ? userDTO.getLastName() : user.getLastName());
        user.setMidleName(userDTO.getMidleName() != null ? userDTO.getMidleName() : user.getMidleName());
        user.setLogin(userDTO.getLogin() != null ? userDTO.getLogin() : user.getLogin());
        user.setPassword(userDTO.getPassword() != null ? userDTO.getPassword() : user.getPassword());
        user.setId(userDTO.getId() != null ? userDTO.getId() : user.getId());
        return user;
    }

}
