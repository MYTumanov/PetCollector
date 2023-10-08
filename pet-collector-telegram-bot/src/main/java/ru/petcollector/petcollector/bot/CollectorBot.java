package ru.petcollector.petcollector.bot;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.abilitybots.api.db.DBContext;
import org.telegram.abilitybots.api.util.AbilityExtension;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class CollectorBot extends AbilityBot {

    @NotNull
    private DBContext db;
    
    @NotNull
    private DebtAbility debtAbility;

    @NotNull
    private UserAbility userAbility;

    public CollectorBot(@NotNull final TelegramBotsApi botsApi,
                        @NotNull final DBContext db,
                        @NotNull final DebtAbility debtAbility,
                        @NotNull final UserAbility userAbility,
                        @Value("${bot.token}") final String token,
                        @Value("${bot.name}") final String userName
    ) throws TelegramApiException {
        super(token, userName, db);
        this.db = db;
        this.debtAbility = debtAbility;
        this.userAbility = userAbility;
        botsApi.registerBot(this);
    }

    @Override
    public long creatorId() {
        return 1L;
    }

    public AbilityExtension debdtAbility() {
        return debtAbility;
    }

    public AbilityExtension userAbility() {
        return userAbility;
    }

}
