package ru.petcollector.petcollector.interfaces.service;

import org.jetbrains.annotations.Nullable;

import ru.petcollector.petcollector.model.UserRegisterRequest;
import ru.petcollector.petcollector.model.UserRegisterResponse;

public interface IUserService {
    
    UserRegisterResponse registerUser(@Nullable final UserRegisterRequest user);

}
