package ru.petcollector.petcollector.bot;

import org.telegram.abilitybots.api.db.DBContext;
import org.telegram.abilitybots.api.objects.Ability;
import org.telegram.abilitybots.api.objects.ReplyFlow;
import org.telegram.abilitybots.api.util.AbilityExtension;

import ru.petcollector.petcollector.handler.DebtHandler;

import static org.telegram.abilitybots.api.objects.Locality.USER;
import static org.telegram.abilitybots.api.objects.Privacy.PUBLIC;
import static ru.petcollector.petcollector.unils.Constans.GET_DEBT_INFO;
import static ru.petcollector.petcollector.unils.Constans.GET_DEBTS;
import static ru.petcollector.petcollector.unils.Constans.NEW_DEBT_REQUEST_ID;

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

    public ReplyFlow newDebtFlow() {
        return ReplyFlow.builder(db).action(debtHandler::addDebt)
                .onlyIf(u -> u.getMessage().getUserShared().getRequestId().equalsIgnoreCase(NEW_DEBT_REQUEST_ID)).build();
    }

}
