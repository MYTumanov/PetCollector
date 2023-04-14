package ru.petcollector.petcollector.service;

import java.util.Optional;

import org.jetbrains.annotations.NotNull;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import ru.petcollector.petcollector.model.user.UserDTO;
import ru.petcollector.petcollector.model.user.UserRegisterRequest;
import ru.petcollector.petcollector.model.user.UserRegisterResponse;

@Service
@PropertySource("classpath:application.properties")
public class UserRegisterService extends UserService {

    @RabbitListener(queues = "${rabbitmq.queue}")
    public UserRegisterResponse readUserFromQueue(@NotNull final UserRegisterRequest input) {
        try {
            System.out.println("Received message: " + input);
            @NotNull final UserDTO userDTO = new UserDTO();
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
    
}
