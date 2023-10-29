package ru.petcollector.petcollector.service;

import java.util.Optional;

import org.jetbrains.annotations.NotNull;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import ru.petcollector.petcollector.exception.EntityNotFoundException;
import ru.petcollector.petcollector.model.user.User;
import ru.petcollector.petcollector.model.user.UserDTO;
import ru.petcollector.petcollector.model.user.UserRegisterRequest;
import ru.petcollector.petcollector.model.user.UserRegisterResponse;
import ru.petcollector.petcollector.model.user.UserTelegramRegisterRequest;

@Service
@PropertySource("classpath:application.properties")
public class UserRegisterService extends UserService {

    @RabbitListener(queues = "${rabbitmq.queue}")
    public UserRegisterResponse readUserFromQueue(@NotNull final UserRegisterRequest input) {
        try {
            @NotNull
            final UserDTO userDTO = new UserDTO();
            userDTO.setLogin(Optional.of(input.getLogin()));
            userDTO.setPassword(Optional.of(input.getPassword()));
            userDTO.setPhoneNumber(Optional.of(input.getPhoneNumber()));
            create(userDTO);
            return new UserRegisterResponse(0, "success");
        } catch (@NotNull final Exception e) {
            e.printStackTrace();
            return new UserRegisterResponse(500, e.getMessage());
        }
    }

    @RabbitListener(queues = "${rabbitmq.queue.telegram}")
    public UserRegisterResponse readUserFromQueue(@NotNull final UserTelegramRegisterRequest input) {
        try {
            @NotNull
            final UserDTO userDTO = new UserDTO();
            if (input.getChatId() != null)
                userDTO.setChatId(Optional.of(input.getChatId()));
            userDTO.setTelegramUserName(Optional.of(input.getTelegramUserName()));
            userDTO.setUserTelegramId(Optional.of(input.getUserTelegramId()));
            try {
                @NotNull
                final User user = findByTelegramId(input.getUserTelegramId());
                userDTO.setVersion(Optional.of(user.getVersion()));
                //userDTO.setId(Optional.of(user.getId()));
            } catch (@NotNull final EntityNotFoundException e) {
                userDTO.setLogin(Optional.of(input.getTelegramUserName()));
                create(userDTO);
                return new UserRegisterResponse(0, "success");
            }
            updateById(userDTO.getId().get(), userDTO);
            return new UserRegisterResponse(0, "success");
        } catch (@NotNull final Exception e) {
            e.printStackTrace();
            return new UserRegisterResponse(500, e.getMessage());
        }
    }

}
