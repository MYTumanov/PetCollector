package ru.petcollector.petcollector.service;

import org.springframework.stereotype.Service;

import ru.petcollector.petcollector.model.User;
import ru.petcollector.petcollector.repository.UserRepository;

@Service
public class UserService extends AbstractService<User, UserRepository> {

}
