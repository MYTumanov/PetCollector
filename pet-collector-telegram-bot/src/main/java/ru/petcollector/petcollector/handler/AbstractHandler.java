package ru.petcollector.petcollector.handler;

import java.util.Map;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import org.telegram.abilitybots.api.db.DBContext;

import static ru.petcollector.petcollector.unils.Constans.USER_IDS;

public abstract class AbstractHandler {

        @NotNull
        protected WebClient webClient;

        @NotNull
        protected DBContext db;

        @NotNull
        private Map<Long, String> userIds;

        public String getUserId(@NotNull final Long userTelegramId) throws WebClientException {
                userIds = db.getMap(USER_IDS);

                return userIds.compute(userTelegramId, (k, v) -> (v != null) ? v
                                : webClient.get()
                                                .uri(uriBuilder -> uriBuilder
                                                                .path("/user/byTelegramId")
                                                                .queryParam("telegramId", userTelegramId)
                                                                .build())
                                                .retrieve()
                                                .bodyToMono(String.class)
                                                .block());
        }

}
