package ru.petcollector.petcollector.api;

import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import ru.petcollector.petcollector.model.AggregateDebt;
import ru.petcollector.petcollector.model.TelegramDebt;

public interface IDebtService {

    @Nullable
    List<AggregateDebt> getDebts(@NotNull final String userId);

    void createDebt(@NotNull final TelegramDebt debt, @NotNull final String userId);

}
