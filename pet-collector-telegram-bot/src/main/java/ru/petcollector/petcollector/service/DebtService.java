package ru.petcollector.petcollector.service;

import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.extern.slf4j.Slf4j;
import ru.petcollector.petcollector.api.IDebtService;
import ru.petcollector.petcollector.model.AggregateDebt;
import ru.petcollector.petcollector.model.TelegramDebt;

@Slf4j
@Service
public class DebtService implements IDebtService {

    @NotNull
    private static final String USERID = "userId";

    @NotNull
    private final WebClient webClient;

    public DebtService(@NotNull final WebClient webClient) {
        this.webClient = webClient;
    }

    @Nullable
    @Override
    public List<AggregateDebt> getDebts(@NotNull String userId) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/debt")
                        .queryParam(USERID, userId)
                        .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<AggregateDebt>>() {
                })
                .block();
    }

    @Override
    public void createDebt(@NotNull final TelegramDebt debt, @NotNull final String userId) {
        log.info("createDebt " + userId);
        webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/debt")
                        .queryParam(USERID, userId)
                        .build())
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(debt)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Nullable
    @Override
    public List<TelegramDebt> getDebtsDetail(@NotNull final String userId, @NotNull final String debtorId) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/debt/debtorid/" + debtorId)
                        .queryParam(USERID, userId)
                        .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<TelegramDebt>>() {
                })
                .block();
    }

    @Override
    public void updateDebt(@NotNull final TelegramDebt debt, @NotNull final String userId) {
        webClient.put()
                .uri(uriBuilder -> uriBuilder
                        .path("/debt/" + debt.getId())
                        .queryParam(USERID, userId)
                        .build())
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(debt)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

}
