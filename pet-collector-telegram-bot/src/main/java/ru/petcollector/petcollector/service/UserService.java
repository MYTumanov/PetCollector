package ru.petcollector.petcollector.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.extern.slf4j.Slf4j;
import ru.petcollector.petcollector.api.IUserService;
import ru.petcollector.petcollector.exception.UserNotFoundException;
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

    @NotNull
    private WebClient webClient;

    public UserService(@NotNull final RabbitTemplate rabbiTemplate, @NotNull final WebClient webClient) {
        this.rabbiTemplate = rabbiTemplate;
        this.webClient = webClient;
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

    @Override
    @NotNull
    public String getUserIdByTelegramId(@NotNull final Long telegramUserId) throws UserNotFoundException {
        final String rs = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/user/byTelegramId")
                        .queryParam("telegramId", telegramUserId)
                        .build())
                .retrieve()
                .onStatus(status -> status.value() == 404,
                        r -> r.bodyToMono(String.class).map(UserNotFoundException::new))
                .bodyToMono(String.class)
                .block();
        if (rs == null)
            throw new UserNotFoundException("UserService.getUserIdByTelegramId rs is null");

        return rs;
    }

    @Override
    @NotNull
    public TelegramUser getUser(@NotNull String userId) throws UserNotFoundException {
        final TelegramUser rs = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/user")
                        .queryParam("userId", userId)
                        .build())
                .retrieve()
                .onStatus(status -> status.value() == 404,
                        r -> r.bodyToMono(String.class).map(UserNotFoundException::new))
                .bodyToMono(TelegramUser.class)
                .block();
        if (rs == null)
            throw new UserNotFoundException("UserService.getUser rs is null");

        return rs;
    }

}
