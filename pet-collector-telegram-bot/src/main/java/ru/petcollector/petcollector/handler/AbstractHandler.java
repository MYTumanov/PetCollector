package ru.petcollector.petcollector.handler;

import java.util.Map;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.web.reactive.function.client.WebClient;
import org.telegram.abilitybots.api.db.DBContext;

import ru.petcollector.petcollector.api.IUserService;
import ru.petcollector.petcollector.exception.UserNotFoundException;

import static ru.petcollector.petcollector.unils.Constans.USER_IDS;

public abstract class AbstractHandler {

    @NotNull
    protected WebClient webClient;

    @NotNull
    protected DBContext db;

    @NotNull
    protected IUserService userService;

    @NotNull
    private Map<Long, String> userIds;

    @Nullable
    public String getUserId(@NotNull final Long userTelegramId) throws UserNotFoundException {
        userIds = db.getMap(USER_IDS);

        if (!userIds.containsKey(userTelegramId)) {
            final String val = userService.getUserIdByTelegramId(userTelegramId);
            userIds.put(userTelegramId, val);
            return val;
        }

        return userIds.get(userTelegramId);
    }

}
