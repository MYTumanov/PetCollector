package ru.petcollector.petcollector.repository;

import org.springframework.stereotype.Repository;

import ru.petcollector.petcollector.model.User;

@Repository
public interface UserRepository extends AbstractRepository<User> {

}
