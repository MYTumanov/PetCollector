package ru.petcollector.petcollector.exception;

import org.jetbrains.annotations.NotNull;

public class EntityCastException extends AbstractPetCollectorException {

    public EntityCastException() {
        super("ERROR! Entity cast exception.");
    }

    public EntityCastException(@NotNull final String message) {
        super(message);
    }

    public EntityCastException(@NotNull final String message, @NotNull final Throwable cause) {
        super(message, cause);
    }

    public EntityCastException(@NotNull final Throwable cause) {
        super(cause);
    }

    public EntityCastException(
            @NotNull final String message,
            @NotNull final Throwable cause,
            final boolean enableSuppression,
            final boolean writableStackTrace
    ) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
