package ru.petcollector.petcollector.repository;

import java.util.Optional;

import org.jetbrains.annotations.NotNull;

import ru.petcollector.petcollector.model.user.User;

public interface UserRepository extends AbstractRepository<User> {

    boolean existsByLogin(@NotNull String login);

    @NotNull
    Optional<User> findByPhoneNumber(@NotNull String login);
}
