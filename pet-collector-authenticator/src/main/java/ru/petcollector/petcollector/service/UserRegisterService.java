package ru.petcollector.petcollector.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import ru.petcollector.petcollector.interfaces.service.IUserService;
import ru.petcollector.petcollector.model.UserRegisterRequest;

@Service
@PropertySource("classpath:application.properties")
public class UserRegisterService implements IUserService {

    @Nullable
    @Value("${rabbitmq.exchange}")
    private String exchange;

    @Nullable
    @Value("${rabbitmq.routingkey}")
    private String routingkey;

    @NotNull
    @Autowired
    private RabbitTemplate rabbiTemplate;

    @Override
    public void registerUser(@Nullable final UserRegisterRequest user) {
        Assert.notNull(exchange, "exchange can't be null");
        Assert.notNull(routingkey, "routing key can't be null");
        Assert.notNull(user, "user can't be null");
        rabbiTemplate.convertAndSend(exchange, routingkey, user);
    }
    
}
