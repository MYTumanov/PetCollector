package ru.petcollector.petcollector.model;

import java.io.Serializable;

import org.jetbrains.annotations.Nullable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TelegramUser implements Serializable {

    @Nullable
    private String userId;

    @Nullable
    private Long userTelegramId;

    @Nullable
    private Long chatId;

    @Nullable
    private String telegramUserName;
    
}
