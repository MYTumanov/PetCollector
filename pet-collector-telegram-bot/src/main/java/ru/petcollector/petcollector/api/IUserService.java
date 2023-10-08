package ru.petcollector.petcollector.api;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import ru.petcollector.petcollector.model.TelegramUser;
import ru.petcollector.petcollector.model.UserRegisterResponse;

public interface IUserService {
    
    @NotNull
    UserRegisterResponse createUser(@Nullable final TelegramUser user) throws IllegalArgumentException;
    
}
