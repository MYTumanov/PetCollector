package ru.petcollector.petcollector.exception;

import org.jetbrains.annotations.NotNull;

public class AbstractTelegramBotException extends Exception {

    protected AbstractTelegramBotException() {
    }

    protected AbstractTelegramBotException(@NotNull final String message) {
        super(message);
    }

    protected AbstractTelegramBotException(@NotNull final String message, @NotNull final Throwable cause) {
        super(message, cause);
    }

    protected AbstractTelegramBotException(@NotNull final Throwable cause) {
        super(cause);
    }

    protected AbstractTelegramBotException(
            @NotNull final String message,
            @NotNull final Throwable cause,
            final boolean enableSuppression,
            final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
