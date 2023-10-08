package ru.petcollector.petcollector.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import lombok.extern.slf4j.Slf4j;
import ru.petcollector.petcollector.api.IUserService;
import ru.petcollector.petcollector.model.TelegramUser;
import ru.petcollector.petcollector.model.UserRegisterResponse;

@Slf4j
@Service
@PropertySource("classpath:application.properties")
public class UserService implements IUserService {

    @Nullable
    @Value("${rabbitmq.exchange}")
    private String exchange;

    @Nullable
    @Value("${rabbitmq.routingkey}")
    private String routingkey;

    @NotNull
    private RabbitTemplate rabbiTemplate;

    public UserService(@NotNull final RabbitTemplate rabbiTemplate) {
        this.rabbiTemplate = rabbiTemplate;
    }

    @NotNull
    @Override
    public UserRegisterResponse createUser(@Nullable final TelegramUser user) throws IllegalArgumentException {
        Assert.notNull(exchange, "exchange can't be null");
        Assert.notNull(routingkey, "routing key can't be null");
        Assert.notNull(user, "user can't be null");
        final UserRegisterResponse rs = rabbiTemplate.convertSendAndReceiveAsType(exchange, routingkey, user,
                new ParameterizedTypeReference<UserRegisterResponse>() {
                });

        log.error("Error code: " + rs.getErrCode());
        log.error("Error message: " + rs.getErrMessage());

        return rs;
    }

}
