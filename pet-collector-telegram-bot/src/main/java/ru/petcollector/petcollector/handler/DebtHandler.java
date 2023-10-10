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
import ru.petcollector.petcollector.model.TelegramDebt;
import ru.petcollector.petcollector.model.TelegramDebtor;
import ru.petcollector.petcollector.model.TelegramUser;
import ru.petcollector.petcollector.unils.AddDebtState;

import static ru.petcollector.petcollector.unils.Constans.ADD_DEBT_STATE;
import static ru.petcollector.petcollector.unils.Constans.UPDATE_ID;
import static ru.petcollector.petcollector.unils.Constans.TEMP_USER;
import static ru.petcollector.petcollector.unils.Constans.TEMP_DEBT;

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
            log.error("DebtHandler.getDebts: ", wcEx);
        }
    }

    public void cancel(@NotNull final MessageContext ctx) {
        log.info("CANCEL " + db.getMap(ADD_DEBT_STATE).get(ctx.chatId()));
        clearMap(ctx.chatId());
    }

    public void addDebtChooseDebtor(@NotNull final BaseAbilityBot bot, @NotNull final Update update) {
        final Long chatId = update.getMessage().getChatId();
        db.getMap(UPDATE_ID).put(chatId, update.getUpdateId());
        final SendMessage message = new SendMessage();
        message.setChatId(chatId);
        try {
            final String userId = getUserId(update.getMessage().getUserShared().getUserId());
            final TelegramUser userDebtor = userService.getUser(userId);
            message.setText(String.format("Сколько злотых должен %s?",
                    userDebtor.getTelegramUserName() == null ? "юзернейм" : userDebtor.getTelegramUserName()));

            db.getMap(TEMP_USER).put(chatId, userDebtor);
            db.getMap(ADD_DEBT_STATE).put(chatId, AddDebtState.ADD_DEBT_SUM);
        } catch (UserNotFoundException e) {
            log.error("DebtHandler.addDebtChooseDebtor: ", e);

            final TelegramUser userDebtor = new TelegramUser();
            userDebtor.setUserTelegramId(update.getMessage().getUserShared().getUserId());
            message.setText("Не знаю о ком ты говоришь, как мне его назвать?");

            db.getMap(TEMP_USER).put(chatId, userDebtor);
            db.getMap(ADD_DEBT_STATE).put(chatId, AddDebtState.DEBTOR_NOT_FOUND);
        }
        bot.silent().execute(message);

    }

    public void addDebtDebtorNotFound(@NotNull final BaseAbilityBot bot, @NotNull final Update update) {
        db.getMap(UPDATE_ID).put(update.getMessage().getChatId(), update.getUpdateId());
        final TelegramUser userDebtor = (TelegramUser) db.getMap(TEMP_USER).get(update.getMessage().getChatId());
        userDebtor.setTelegramUserName(update.getMessage().getText());
        userService.createUser(userDebtor);

        final SendMessage message = new SendMessage();
        message.setChatId(update.getMessage().getChatId());
        message.setText(String.format("Сколько злотых должен %s?",
                userDebtor.getTelegramUserName() == null ? "юзернейм" : userDebtor.getTelegramUserName()));
        bot.silent().execute(message);

        db.getMap(ADD_DEBT_STATE).put(update.getMessage().getChatId(), AddDebtState.ADD_DEBT_SUM);
    }

    public void addDebtSum(@NotNull final BaseAbilityBot bot, @NotNull final Update update) {
        db.getMap(UPDATE_ID).put(update.getMessage().getChatId(), update.getUpdateId());
        final SendMessage message = new SendMessage();
        try {
            final Float debtSum = Float.parseFloat(update.getMessage().getText());
            final TelegramUser userDebtor = (TelegramUser) db.getMap(TEMP_USER).get(update.getMessage().getChatId());
            final TelegramDebt debt = new TelegramDebt();
            final TelegramDebtor debtor = new TelegramDebtor();
            debtor.setSum(debtSum);
            debtor.setUserId(userDebtor.getUserId());
            debt.getDebtors().add(debtor);
            db.getMap(TEMP_DEBT).put(update.getMessage().getChatId(), debt);

            message.setChatId(update.getMessage().getChatId());
            message.setText("Описание");
            bot.silent().execute(message);
        } catch (final NullPointerException nulExc) {
            message.setChatId(update.getMessage().getChatId());
            message.setText("Нужна сумма");
            bot.silent().execute(message);
        } catch (final NumberFormatException numExc) {
            message.setChatId(update.getMessage().getChatId());
            message.setText("Сумма должна быть числом");
            bot.silent().execute(message);
        }
        db.getMap(ADD_DEBT_STATE).put(update.getMessage().getChatId(), AddDebtState.ADD_DEBT_DESC);
    }

    public void addDebtDesc(@NotNull final BaseAbilityBot bot, @NotNull final Update update) {
        db.getMap(UPDATE_ID).put(update.getMessage().getChatId(), update.getUpdateId());
        final TelegramDebt debt = (TelegramDebt) db.getMap(TEMP_DEBT).get(update.getMessage().getChatId());
        debt.setComment(update.getMessage().getText());
        final SendMessage message = new SendMessage();
        message.setChatId(update.getMessage().getChatId());
        message.setText("Сохраняю?");
        bot.silent().execute(message);
        db.getMap(ADD_DEBT_STATE).put(update.getMessage().getChatId(), AddDebtState.ADD_DEBT);
    }

    public void addDebt(@NotNull final BaseAbilityBot bot, @NotNull final Update update) {
        final SendMessage message = new SendMessage();
        try {
            debtService.createDebt((TelegramDebt) db.getMap(TEMP_DEBT).get(update.getMessage().getChatId()),
                    getUserId(update.getChatMember().getFrom().getId()));
            message.setChatId(update.getMessage().getChatId());
            message.setText("Отправил");
            bot.silent().execute(message);
        } catch (final WebClientException | UserNotFoundException e) {
            message.setChatId(update.getMessage().getChatId());
            message.setText("У меня лапки :(");
            bot.silent().execute(message);
            log.error("DebtHandler.addDebt: ", e);
        } finally {
            clearMap(update.getMessage().getChatId());
        }
    }

    private void clearMap(@NotNull final Long chatId) {
        db.getMap(ADD_DEBT_STATE).put(chatId, AddDebtState.CHOOSE_DETOR);
        db.getMap(UPDATE_ID).remove(chatId);
        db.getMap(TEMP_USER).remove(chatId);
        db.getMap(TEMP_DEBT).remove(chatId);
    }

}
