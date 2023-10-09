package ru.petcollector.petcollector.handler;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.telegram.abilitybots.api.db.DBContext;
import org.telegram.abilitybots.api.objects.MessageContext;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import lombok.extern.slf4j.Slf4j;
import ru.petcollector.petcollector.api.IUserService;
import ru.petcollector.petcollector.exception.UserNotFoundException;
import ru.petcollector.petcollector.keyboard.KeyBoardFactory;
import ru.petcollector.petcollector.model.TelegramUser;
import ru.petcollector.petcollector.unils.AddDebtState;

import static ru.petcollector.petcollector.unils.Constans.ADD_DEBT_STATE;

@Slf4j
@Component
public class UserHandler extends AbstractHandler {

    public UserHandler(@NotNull final WebClient webClient, @NotNull final DBContext db,
            @NotNull final IUserService userService) {
        this.webClient = webClient;
        this.db = db;
        this.userService = userService;
    }

    public void userStart(@NotNull final MessageContext ctx) {
        final TelegramUser user = new TelegramUser();
        user.setChatId(ctx.chatId());
        user.setTelegramUserName(ctx.user().getUserName());
        user.setUserTelegramId(ctx.user().getId());
        try {
            final String userId = getUserId(ctx.user().getId());
            user.setUserId(userId);
        } catch (@NotNull final UserNotFoundException e) {
            log.info("User " + ctx.user().getId() + " not found");
        }

        userService.createUser(user);
        final SendMessage message = new SendMessage();
        message.setChatId(ctx.chatId());
        message.setText("Кто тебе должен?");
        message.setReplyMarkup(KeyBoardFactory.UserRequestKeyboard());

        ctx.bot().silent().execute(message);

        db.getMap(ADD_DEBT_STATE).put(ctx.chatId(), AddDebtState.CHOOSE_DETOR);
    }

}