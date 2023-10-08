package ru.petcollector.petcollector.handler;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.reactive.function.client.WebClient;
import org.telegram.abilitybots.api.db.DBContext;
import org.telegram.abilitybots.api.objects.MessageContext;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import lombok.extern.slf4j.Slf4j;
import ru.petcollector.petcollector.keyboard.KeyBoardFactory;
import ru.petcollector.petcollector.model.TelegramUser;
import ru.petcollector.petcollector.model.UserRegisterResponse;
import ru.petcollector.petcollector.unils.AddDebtState;

import static ru.petcollector.petcollector.unils.Constans.ADD_DEBT_STATE;

@Slf4j
@Component
@PropertySource("classpath:application.properties")
public class UserHandler extends AbstractHandler {

    @Nullable
    @Value("${rabbitmq.exchange}")
    private String exchange;

    @Nullable
    @Value("${rabbitmq.routingkey}")
    private String routingkey;

    @NotNull
    private RabbitTemplate rabbiTemplate;

    public UserHandler(@NotNull final WebClient webClient, @NotNull final RabbitTemplate rabbiTemplate,
            @NotNull final DBContext db) {
        this.webClient = webClient;
        this.rabbiTemplate = rabbiTemplate;
        this.db = db;
    }

    public void userStart(@NotNull final MessageContext ctx) {
        final String userId = getUserId(ctx.user().getId());
        final TelegramUser user = new TelegramUser();
        user.setChatId(ctx.chatId());
        user.setTelegramUserName(ctx.user().getUserName());
        user.setUserTelegramId(ctx.user().getId());
        user.setUserId(userId);

        Assert.notNull(exchange, "exchange can't be null");
        Assert.notNull(routingkey, "routing key can't be null");
        Assert.notNull(user, "user can't be null");
        final UserRegisterResponse rs = rabbiTemplate.convertSendAndReceiveAsType(exchange, routingkey, user,
                new ParameterizedTypeReference<UserRegisterResponse>() {
                });
        log.error("Error code: " + rs.getErrCode());
        log.error("Error message: " + rs.getErrMessage());

        final SendMessage message = new SendMessage();
        message.setChatId(ctx.chatId());
        message.setText("Кто тебе должен?");
        message.setReplyMarkup(KeyBoardFactory.UserRequestKeyboard());

        ctx.bot().silent().execute(message);

        db.getMap(ADD_DEBT_STATE).put(ctx.chatId(), AddDebtState.CHOOSE_DETOR);

    }

}
