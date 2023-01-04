package ru.petcollector.petcollector.exception;

import org.jetbrains.annotations.NotNull;

public class EntityNotFoundException extends AbstractPetCollectorException {

    public EntityNotFoundException() {
        super("ERROR! Entity is not found.");
    }

    public EntityNotFoundException(@NotNull final String message) {
        super(message);
    }

    public EntityNotFoundException(@NotNull final String message, @NotNull final Throwable cause) {
        super(message, cause);
    }

    public EntityNotFoundException(@NotNull final Throwable cause) {
        super(cause);
    }

    public EntityNotFoundException(
            @NotNull final String message,
            @NotNull final Throwable cause,
            final boolean enableSuppression,
            final boolean writableStackTrace
    ) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
