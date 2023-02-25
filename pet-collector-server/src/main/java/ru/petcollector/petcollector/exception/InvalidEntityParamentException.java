package ru.petcollector.petcollector.exception;

import org.jetbrains.annotations.NotNull;

public class InvalidEntityParamentException extends AbstractPetCollectorException {

    public InvalidEntityParamentException() {
        super("ERROR! Invalid entity parameter.");
    }

    public InvalidEntityParamentException(@NotNull final String message) {
        super(message);
    }

    public InvalidEntityParamentException(@NotNull final String message, @NotNull final Throwable cause) {
        super(message, cause);
    }

    public InvalidEntityParamentException(@NotNull final Throwable cause) {
        super(cause);
    }

    public InvalidEntityParamentException(
            @NotNull final String message,
            @NotNull final Throwable cause,
            final boolean enableSuppression,
            final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
