package ru.petcollector.petcollector.bot;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.telegram.abilitybots.api.objects.Ability;
import org.telegram.abilitybots.api.util.AbilityExtension;

import ru.petcollector.petcollector.handler.UserHandler;

import static org.telegram.abilitybots.api.objects.Locality.USER;
import static org.telegram.abilitybots.api.objects.Privacy.PUBLIC;

@Component
public class UserAbility implements AbilityExtension {

    @NotNull
    private UserHandler userHandler;

    public UserAbility(@NotNull final UserHandler userHandler) {
        this.userHandler = userHandler;
    }

    public Ability startCommand() {
        return Ability.builder()
                .name("start")
                .info("Info for start")
                .locality(USER)
                .privacy(PUBLIC)
                .action(userHandler::userStart)
                .build();
    }

}
