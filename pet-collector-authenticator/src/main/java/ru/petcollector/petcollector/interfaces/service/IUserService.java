package ru.petcollector.petcollector.interfaces.service;

import org.jetbrains.annotations.Nullable;

import ru.petcollector.petcollector.model.UserRegisterRequest;

public interface IUserService {
    
    void registerUser(@Nullable final UserRegisterRequest user);

}
