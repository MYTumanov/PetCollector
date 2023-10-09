package ru.petcollector.petcollector.api;

import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import ru.petcollector.petcollector.model.AggregateDebt;

public interface IDebtService {

    @Nullable
    List<AggregateDebt> getDebts(@NotNull final String userId);
    
}
