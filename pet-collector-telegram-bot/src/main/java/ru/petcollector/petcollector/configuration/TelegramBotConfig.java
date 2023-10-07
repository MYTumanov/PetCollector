package ru.petcollector.petcollector.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.abilitybots.api.db.DBContext;
import org.telegram.abilitybots.api.db.MapDBContext;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Configuration
public class TelegramBotConfig {

    @Bean
    public TelegramBotsApi defaultBot() throws TelegramApiException {
        return new TelegramBotsApi(DefaultBotSession.class);
    }

    @Bean
    public DBContext dbProducer() {
        return MapDBContext.onlineInstance("./pet-collector-telegram-bot/target/pet_collector_bot_db");
    }

}
