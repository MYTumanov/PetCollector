package ru.petcollector.petcollector.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Repository;

import ru.petcollector.petcollector.model.User;

@Repository
public interface UserRepository extends AbstractRepository<User> {

    boolean existsByLogin(@NotNull String login);

    @Nullable
    User findByLogin(@NotNull String login);

}
