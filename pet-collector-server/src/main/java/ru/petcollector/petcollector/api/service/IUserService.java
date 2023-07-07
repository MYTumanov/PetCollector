package ru.petcollector.petcollector.api.service;

import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import ru.petcollector.petcollector.exception.EntityNotFoundException;
import ru.petcollector.petcollector.exception.InvalidEntityParamentException;
import ru.petcollector.petcollector.model.user.User;
import ru.petcollector.petcollector.model.user.UserDTO;

public interface IUserService {

    boolean existsByLogin(@Nullable final String login) throws IllegalArgumentException;

    @NotNull
    User findById(@Nullable final String id) throws IllegalArgumentException, EntityNotFoundException;

    @Nullable
    User findByPhoneNumber(@Nullable final String phoneNumber) throws IllegalArgumentException;

    @NotNull
    User updateById(@Nullable final String id, @Nullable final UserDTO user) throws IllegalArgumentException, EntityNotFoundException, InvalidEntityParamentException;

    @NotNull
    User create(@Nullable final UserDTO user);

    void deleteById(@Nullable final String id) throws IllegalArgumentException, EntityNotFoundException;

    @NotNull
    @Deprecated
    public List<User> findAll();

}
