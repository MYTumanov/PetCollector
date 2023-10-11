package ru.petcollector.petcollector.service;

import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.extern.slf4j.Slf4j;
import ru.petcollector.petcollector.api.IDebtService;
import ru.petcollector.petcollector.model.AggregateDebt;
import ru.petcollector.petcollector.model.TelegramDebt;

@Slf4j
@Service
public class DebtService implements IDebtService {

    @NotNull
    private WebClient webClient;

    public DebtService(@NotNull final WebClient webClient) {
        this.webClient = webClient;
    }

    @Nullable
    @Override
    public List<AggregateDebt> getDebts(@NotNull String userId) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/debt")
                        .queryParam("userId", userId)
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
                        .queryParam("userId", userId)
                        .build())
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(debt)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

}
