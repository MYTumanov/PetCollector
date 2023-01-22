package ru.petcollector.petcollector.repository;

import java.util.Optional;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import ru.petcollector.petcollector.model.Client;

@Repository
public interface ClientRepository extends MongoRepository<Client, String> {

    public Optional<Client> findByClientId(@NotNull final String clientId);

}
