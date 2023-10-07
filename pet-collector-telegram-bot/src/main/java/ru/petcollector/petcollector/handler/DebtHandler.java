package ru.petcollector.petcollector.handler;

import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.telegram.abilitybots.api.bot.BaseAbilityBot;
import org.telegram.abilitybots.api.objects.MessageContext;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import reactor.core.publisher.Mono;
import ru.petcollector.petcollector.model.AggregateDebt;

@Component
public class DebtHandler extends AbstractHandler {

    public DebtHandler(@NotNull final WebClient webClient) {
        this.webClient = webClient;
    }

    public void getDebts(@NotNull final MessageContext ctx) {
        final String userId = getUserId(ctx);
        Mono<List<AggregateDebt>> response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/debt")
                        .queryParam("userId", userId)
                        .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<AggregateDebt>>() {
                });

        List<AggregateDebt> debts = response.block();
        StringBuilder stringBuilder = new StringBuilder();
        for (AggregateDebt debt : debts) {
            stringBuilder.append(debt + "\r\n");
        }

        final SendMessage message = new SendMessage();
        message.setChatId(ctx.chatId());
        message.setText(stringBuilder.isEmpty() ? "Пусто" : stringBuilder.toString());

        ctx.bot().silent().execute(message);
    }

    public void addDebt(@NotNull final BaseAbilityBot bot, @NotNull final Update update) {

    }

}
