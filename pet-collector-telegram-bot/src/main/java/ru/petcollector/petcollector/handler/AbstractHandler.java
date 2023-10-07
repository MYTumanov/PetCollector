package ru.petcollector.petcollector.handler;

import java.util.Map;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.reactive.function.client.WebClient;
import org.telegram.abilitybots.api.objects.MessageContext;

public abstract class AbstractHandler {

    @NotNull
    protected WebClient webClient;

    @NotNull
    private Map<Long, String> userIds;

    public String getUserId(@NotNull final MessageContext ctx) {
        final Long telegramId = ctx.user().getId();
        userIds = ctx.bot().db().getMap("UIDS");

        return userIds.compute(telegramId, (k, v) -> (v != null) ? v
                : webClient.get()
                        .uri(uriBuilder -> uriBuilder
                                .path("/user/byTelegramId")
                                .queryParam("telegramId", ctx.user().getId())
                                .build())
                        .retrieve()
                        .bodyToMono(String.class)
                        .block());
    }

}
