package ru.petcollector.petcollector.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import ru.petcollector.petcollector.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    
}
