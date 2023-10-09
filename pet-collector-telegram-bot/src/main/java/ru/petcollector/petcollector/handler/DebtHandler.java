package ru.petcollector.petcollector.handler;

import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import org.telegram.abilitybots.api.bot.BaseAbilityBot;
import org.telegram.abilitybots.api.db.DBContext;
import org.telegram.abilitybots.api.objects.MessageContext;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import lombok.extern.slf4j.Slf4j;
import ru.petcollector.petcollector.api.IDebtService;
import ru.petcollector.petcollector.api.IUserService;
import ru.petcollector.petcollector.exception.UserNotFoundException;
import ru.petcollector.petcollector.model.AggregateDebt;
import ru.petcollector.petcollector.model.TelegramUser;
import ru.petcollector.petcollector.unils.AddDebtState;

import static ru.petcollector.petcollector.unils.Constans.ADD_DEBT_STATE;
import static ru.petcollector.petcollector.unils.Constans.UPDATE_ID;
import static ru.petcollector.petcollector.unils.Constans.TEMP_USER;;

@Slf4j
@Component
public class DebtHandler extends AbstractHandler {

    @NotNull
    private IDebtService debtService;

    public DebtHandler(@NotNull final WebClient webClient, @NotNull final DBContext db,
            @NotNull final IUserService userService, @NotNull final IDebtService debtService) {
        this.webClient = webClient;
        this.db = db;
        this.userService = userService;
        this.debtService = debtService;
    }

    public void getDebts(@NotNull final MessageContext ctx) {
        try {
            @Nullable
            final String userId = getUserId(ctx.user().getId());
            if (userId == null || userId.isEmpty())
                throw new UserNotFoundException();

            List<AggregateDebt> debts = debtService.getDebts(userId);
            StringBuilder stringBuilder = new StringBuilder();
            for (AggregateDebt debt : debts) {
                stringBuilder.append(debt + "\r\n");
            }

            final SendMessage message = new SendMessage();
            message.setChatId(ctx.chatId());
            message.setText(stringBuilder.isEmpty() ? "Пусто" : stringBuilder.toString());

            ctx.bot().silent().execute(message);
        } catch (@NotNull final UserNotFoundException userEx) {
            log.error("User not found", userEx);
        } catch (@NotNull final WebClientException wcEx) {
            log.error("DebtHandler.getDebts ", wcEx);
        }
    }

    public void cancel(@NotNull final MessageContext ctx) {
        log.info("CANCEL " + db.getMap(ADD_DEBT_STATE).get(ctx.chatId()));
        db.getMap(ADD_DEBT_STATE).put(ctx.chatId(), AddDebtState.CHOOSE_DETOR);
    }

    // TODO добавить сохранение дебта
    public void addDebtChooseDebtor(@NotNull final BaseAbilityBot bot, @NotNull final Update update) {
        final Long chatId = update.getMessage().getChatId();
        db.getMap(UPDATE_ID).put(chatId, update.getUpdateId());
        final SendMessage message = new SendMessage();
        message.setChatId(chatId);
        try {
            final String userId = getUserId(update.getMessage().getUserShared().getUserId());
            // TODO получить имя юзера

            message.setText("Сколько злотых ожидаете от 'ЮЗЕРНЕЙ'?");

            db.getMap(ADD_DEBT_STATE).put(chatId, AddDebtState.ADD_DEBT_SUM);
        } catch (UserNotFoundException e) {
            log.error("addDebtChooseDebtor: ", e.getMessage());

            final TelegramUser user = new TelegramUser();
            user.setUserTelegramId(update.getMessage().getUserShared().getUserId());
            message.setText("Не знаю о ком ты говоришь, как мне его назвать?");

            db.getMap(TEMP_USER).put(chatId, user);
            db.getMap(ADD_DEBT_STATE).put(chatId, AddDebtState.DEBTOR_NOT_FOUND);
        }
        bot.silent().execute(message);

    }

    public void addDebtDebtorNotFound(@NotNull final BaseAbilityBot bot, @NotNull final Update update) {
        db.getMap(UPDATE_ID).put(update.getMessage().getChatId(), update.getUpdateId());
        final TelegramUser user = (TelegramUser) db.getMap(TEMP_USER).get(update.getMessage().getChatId());
        user.setTelegramUserName(update.getMessage().getText());
        userService.createUser(user);

        final SendMessage message = new SendMessage();
        message.setChatId(update.getMessage().getChatId());
        message.setText("Сколько злотых ожидаете от " + user.getTelegramUserName() + "?");
        bot.silent().execute(message);

        db.getMap(ADD_DEBT_STATE).put(update.getMessage().getChatId(), AddDebtState.ADD_DEBT_SUM);
    }

    public void addDebtSum(@NotNull final BaseAbilityBot bot, @NotNull final Update update) {
        db.getMap(UPDATE_ID).put(update.getMessage().getChatId(), update.getUpdateId());

        final SendMessage message = new SendMessage();
        message.setChatId(update.getMessage().getChatId());
        message.setText("Описание");
        bot.silent().execute(message);

    }

    public void addDebtDesc(@NotNull final BaseAbilityBot bot, @NotNull final Update update) {
        db.getMap(UPDATE_ID).put(update.getMessage().getChatId(), update.getUpdateId());
    }

    public void addDebt(@NotNull final BaseAbilityBot bot, @NotNull final Update update) {
        db.getMap(UPDATE_ID).put(update.getMessage().getChatId(), update.getUpdateId());
    }

}
