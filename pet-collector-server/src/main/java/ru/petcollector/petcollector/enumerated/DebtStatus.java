package ru.petcollector.petcollector.enumerated;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public enum DebtStatus {

    IN_PROCCESS("In proccess"),
    PAID("Paid"),
    CANCELED("Canceled"),
    DECLAINED("Declained");

    @NotNull
    private final String displayName;

    DebtStatus(@NotNull final String displayName) {
        this.displayName = displayName;
    }

    @NotNull
    public static String toName(@Nullable final DebtStatus status) {
        if (status == null)
            return "";
        return status.getDisplayName();
    }

    @Nullable
    public static DebtStatus toStatus(@NotNull final String value) {
        if (value.isEmpty())
            return null;
        for (final DebtStatus status : values()) {
            if (status.name().equals(value))
                return status;
        }
        return null;
    }

    @NotNull
    public String getDisplayName() {
        return displayName;
    }

}
