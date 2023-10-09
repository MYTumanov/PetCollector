package ru.petcollector.petcollector.exception;

import org.jetbrains.annotations.NotNull;

public class UserNotFoundException extends AbstractTelegramBotException {

    public UserNotFoundException() {
        super("ERROR! User is not found.");
    }

    public UserNotFoundException(@NotNull final String message) {
        super(message);
    }

    public UserNotFoundException(@NotNull final String message, @NotNull final Throwable cause) {
        super(message, cause);
    }

    public UserNotFoundException(@NotNull final Throwable cause) {
        super(cause);
    }

    public UserNotFoundException(
            @NotNull final String message,
            @NotNull final Throwable cause,
            final boolean enableSuppression,
            final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
