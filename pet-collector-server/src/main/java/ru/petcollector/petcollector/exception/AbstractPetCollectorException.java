package ru.petcollector.petcollector.exception;

import org.jetbrains.annotations.NotNull;

public abstract class AbstractPetCollectorException extends Exception {
    
    protected AbstractPetCollectorException() {
    }

    protected AbstractPetCollectorException(@NotNull final String message) {
        super(message);
    }

    protected AbstractPetCollectorException(@NotNull final String message, @NotNull final Throwable cause) {
        super(message, cause);
    }

    protected AbstractPetCollectorException(@NotNull final Throwable cause) {
        super(cause);
    }

    protected AbstractPetCollectorException(
            @NotNull final String message,
            @NotNull final Throwable cause,
            final boolean enableSuppression,
            final boolean writableStackTrace
    ) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
