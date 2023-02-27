package ru.petcollector.petcollector.service;

import java.util.Optional;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Service;

import ru.petcollector.petcollector.api.service.IUserService;
import ru.petcollector.petcollector.exception.EntityNotFoundException;
import ru.petcollector.petcollector.exception.InvalidEntityParamentException;
import ru.petcollector.petcollector.map.UserMapper;
import ru.petcollector.petcollector.model.user.User;
import ru.petcollector.petcollector.model.user.UserDTO;
import ru.petcollector.petcollector.repository.UserRepository;

@Service
public class UserService extends AbstractService<User, UserRepository> implements IUserService {

    @Override
    public boolean existsByLogin(@Nullable final String login) throws IllegalArgumentException {
        if (login == null || login.isEmpty())
            throw new IllegalArgumentException("login");
        return repository.existsByLogin(login);
    }

    @NotNull
    @Override
    public User create(@Nullable final UserDTO userDTO) {
        if (userDTO == null)
            throw new IllegalArgumentException();
        userDTO.setVersion(Optional.of(0d));
        @NotNull
        final User user = UserMapper.map(userDTO, new User());
        return repository.save(user);
    }

    @NotNull
    @Override
    public User updateById(@Nullable final String id, @Nullable final UserDTO userDTO)
            throws EntityNotFoundException, InvalidEntityParamentException {
        if (id == null || id.isEmpty())
            throw new IllegalArgumentException("id");
        if (userDTO == null)
            throw new IllegalArgumentException("userDTO");
        if (!userDTO.getVersion().isPresent())
            throw new InvalidEntityParamentException("version");
        return repository.save(UserMapper.map(userDTO, findById(id)));
    }

    @Nullable
    @Override
    public User findByPhoneNumber(@Nullable final String phoneNumber) throws IllegalArgumentException {
        if (phoneNumber == null || phoneNumber.isEmpty())
            throw new IllegalArgumentException("phoneNumber");
        return repository.findByPhoneNumber(phoneNumber).orElse(null);
    }

}
