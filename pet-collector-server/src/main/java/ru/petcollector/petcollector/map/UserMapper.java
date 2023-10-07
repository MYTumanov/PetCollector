package ru.petcollector.petcollector.map;

import org.jetbrains.annotations.NotNull;

import ru.petcollector.petcollector.model.user.User;
import ru.petcollector.petcollector.model.user.UserDTO;

public interface UserMapper {

    public static User map(@NotNull final UserDTO userDTO, @NotNull final User user) {
        user.setFirstName(userDTO.getFirstName().orElse(user.getFirstName()));
        user.setLastName(userDTO.getLastName().orElse(user.getLastName()));
        user.setMidleName(userDTO.getMidleName().orElse(user.getMidleName()));
        user.setLogin(userDTO.getLogin().orElse(user.getLogin()));
        user.setPassword(userDTO.getPassword().orElse(user.getPassword()));
        user.setPhoneNumber(userDTO.getPhoneNumber().orElse(user.getPhoneNumber()));
        user.setVersion(userDTO.getVersion().orElse(user.getVersion()));

        user.setTelegramUserName(userDTO.getTelegramUserName().orElse(user.getTelegramUserName()));
        user.setUserTelegramId(userDTO.getUserTelegramId().orElse(user.getUserTelegramId()));
        user.setChatId(userDTO.getChatId().orElse(user.getChatId()));
        return user;
    }

}
