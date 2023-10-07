package ru.petcollector.petcollector.bot;

import org.telegram.abilitybots.api.objects.Ability;
import org.telegram.abilitybots.api.util.AbilityExtension;

import ru.petcollector.petcollector.handler.DebtHandler;

import static org.telegram.abilitybots.api.objects.Locality.USER;
import static org.telegram.abilitybots.api.objects.Privacy.PUBLIC;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class DebtAbility implements AbilityExtension {

    @NotNull
    private DebtHandler debtHandler;

    public DebtAbility (final DebtHandler debtHandler) {
        this.debtHandler = debtHandler;
    }

    public Ability getDebtCommand() {
        return Ability.builder()
                .name("getdebts")
                .info("Info for start")
                .locality(USER)
                .privacy(PUBLIC)
                .action(debtHandler::getDebts)
                .build();
    }

}
