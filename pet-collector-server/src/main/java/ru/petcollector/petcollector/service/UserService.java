package ru.petcollector.petcollector.service;

import java.util.List;
import java.util.Optional;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.petcollector.petcollector.model.User;
import ru.petcollector.petcollector.repository.UserRepository;

@Service
public class UserService {
    
    @NotNull
    @Autowired
    private UserRepository repository;

    public User findById(@NotNull final String id) {
        @NotNull Optional<User> userOpt = repository.findById(id);
        if (!userOpt.isPresent()) {
            return new User();
        }
        return userOpt.get();
    }

    public List<User> findAll() {
        return repository.findAll();
    }

}
