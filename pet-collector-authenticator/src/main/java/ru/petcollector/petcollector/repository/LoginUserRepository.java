package ru.petcollector.petcollector.repository;

import java.util.Optional;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import ru.petcollector.petcollector.model.LoginUser;

@Repository
public interface LoginUserRepository extends MongoRepository<LoginUser, String> {

    public Optional<LoginUser> findByLogin(@NotNull final String login);

}
