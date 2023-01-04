package ru.petcollector.petcollector.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Service;

import ru.petcollector.petcollector.exception.AbstractPetCollectorException;
import ru.petcollector.petcollector.exception.EntityNotFoundException;
import ru.petcollector.petcollector.model.User;
import ru.petcollector.petcollector.repository.UserRepository;

@Service
public class UserService extends AbstractService<User, UserRepository> {

    public boolean existByLogin(@Nullable final String login) throws IllegalArgumentException {
        if (login == null)
            throw new IllegalArgumentException("login is null");
        return repository.existsByLogin(login);
    }

    @Nullable
    public User findByLogin(@Nullable final String login) throws IllegalArgumentException {
        if (login == null)
            throw new IllegalArgumentException("login is null");
        return repository.findByLogin(login);
    }

    public void deleteByLogin(@Nullable final String login) throws AbstractPetCollectorException, IllegalArgumentException {
        if (login == null)
            throw new IllegalArgumentException("login is null");
        @NotNull
        User user = repository.findByLogin(login);
        if (user == null) {
            throw new EntityNotFoundException("Entity not found by login: " + login);
        }
        user.setDeleted(true);
        repository.save(user);
    }

}
