package ru.petcollector.petcollector.bot;

import org.telegram.abilitybots.api.db.DBContext;
import org.telegram.abilitybots.api.objects.Ability;
import org.telegram.abilitybots.api.objects.ReplyFlow;
import org.telegram.abilitybots.api.util.AbilityExtension;

import ru.petcollector.petcollector.handler.DebtHandler;
import ru.petcollector.petcollector.unils.AddDebtState;

import static org.telegram.abilitybots.api.objects.Locality.USER;
import static org.telegram.abilitybots.api.objects.Privacy.PUBLIC;
import static ru.petcollector.petcollector.unils.Constans.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class DebtAbility implements AbilityExtension {

    @NotNull
    private DebtHandler debtHandler;

    @NotNull
    private DBContext db;

    public DebtAbility(final DebtHandler debtHandler, final DBContext db) {
        this.debtHandler = debtHandler;
        this.db = db;
    }

    @NotNull
    public Ability getDebtCommand() {
        return Ability.builder()
                .name(GET_DEBTS)
                .info(GET_DEBT_INFO)
                .locality(USER)
                .privacy(PUBLIC)
                .action(debtHandler::getDebts)
                .build();
    }

    @NotNull
    public Ability cancelDebtCommand() {
        return Ability.builder()
                .name(CANCEL_COMMAND)
                .info(CANCEL_COMMAND_INFO)
                .locality(USER)
                .privacy(PUBLIC)
                .action(debtHandler::cancel)
                .build();
    }

    public ReplyFlow newDebtFlow() {
        @NotNull
        final ReplyFlow addDebt = ReplyFlow.builder(db).action(debtHandler::addDebt)
                .onlyIf(u -> db.getMap(ADD_DEBT_STATE).get(u.getMessage().getChatId())
                        .equals(AddDebtState.ADD_DEBT)
                        && !db.getMap(UPDATE_ID).get(u.getMessage().getChatId()).equals(u.getUpdateId())
                        && !u.getMessage().isCommand())
                .build();

        @NotNull
        final ReplyFlow addDebtDescFlow = ReplyFlow.builder(db).action(debtHandler::addDebtDesc)
                .onlyIf(u -> db.getMap(ADD_DEBT_STATE).get(u.getMessage().getChatId())
                        .equals(AddDebtState.ADD_DEBT_DESC)
                        && !db.getMap(UPDATE_ID).get(u.getMessage().getChatId()).equals(u.getUpdateId())
                        && !u.getMessage().isCommand())
                .next(addDebt)
                .build();

        @NotNull
        final ReplyFlow addDebtSumFlow = ReplyFlow.builder(db).action(debtHandler::addDebtSum)
                .onlyIf(u -> (db.getMap(ADD_DEBT_STATE).get(u.getMessage().getChatId())
                        .equals(AddDebtState.ADD_DEBT_SUM)
                        && !db.getMap(UPDATE_ID).get(u.getMessage().getChatId()).equals(u.getUpdateId()))
                        && !u.getMessage().isCommand())
                .next(addDebtDescFlow)
                .build();

        @NotNull
        final ReplyFlow debtorNotFoundFlow = ReplyFlow.builder(db).action(debtHandler::addDebtDebtorNotFound)
                .onlyIf(u -> (db.getMap(ADD_DEBT_STATE).get(u.getMessage().getChatId())
                        .equals(AddDebtState.DEBTOR_NOT_FOUND)
                        && !db.getMap(UPDATE_ID).get(u.getMessage().getChatId()).equals(u.getUpdateId()))
                        && !u.getMessage().isCommand())
                .next(addDebtSumFlow)
                .build();

        return ReplyFlow.builder(db).action(debtHandler::addDebtChooseDebtor)
                .onlyIf(u -> (u.getMessage().getUserShared().getRequestId().equalsIgnoreCase(NEW_DEBT_REQUEST_ID)
                        && db.getMap(ADD_DEBT_STATE).get(u.getMessage().getChatId())
                                .equals(AddDebtState.CHOOSE_DETOR)))
                .next(addDebtSumFlow)
                .next(debtorNotFoundFlow)
                .build();
    }

}
