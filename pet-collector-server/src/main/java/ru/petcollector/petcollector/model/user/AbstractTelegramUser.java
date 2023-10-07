package ru.petcollector.petcollector.model.user;

import org.jetbrains.annotations.Nullable;

import lombok.Getter;
import lombok.Setter;
import ru.petcollector.petcollector.model.AbstractModel;

@Getter
@Setter
public abstract class AbstractTelegramUser extends AbstractModel{
    
    @Nullable
    private Long userTelegramId;

    @Nullable
    private Long chatId;

    @Nullable
    private String telegramUserName;

}
