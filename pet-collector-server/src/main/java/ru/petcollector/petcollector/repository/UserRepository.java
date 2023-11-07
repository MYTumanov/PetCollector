package ru.petcollector.petcollector.repository;

import java.util.List;
import java.util.Optional;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;

import ru.petcollector.petcollector.model.user.User;

public interface UserRepository extends AbstractRepository<User> {

    boolean existsByWebUser_Login(final String login);

    @NotNull
    Optional<User> findByWebUser_PhoneNumber(final String login);

    @NotNull
    @EntityGraph(type = EntityGraphType.FETCH, value = "user-extuser-graph")
    Optional<User> findByTelegramUser_UserTelegramId(final Long telegramId);

    @NotNull
    @EntityGraph(type = EntityGraphType.FETCH, value = "user-extuser-graph")
    Optional<User> findById(final String id);

    @NotNull
    @EntityGraph(type = EntityGraphType.FETCH, value = "user-extuser-graph")
    List<User> findAll();

}
