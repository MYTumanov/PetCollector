package ru.petcollector.petcollector.api.service;

import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import ru.petcollector.petcollector.exception.EntityNotFoundException;
import ru.petcollector.petcollector.exception.InvalidEntityParamentException;
import ru.petcollector.petcollector.model.debt.AggregateDebt;
import ru.petcollector.petcollector.model.debt.Debt;
import ru.petcollector.petcollector.model.debt.DebtDTO;
import ru.petcollector.petcollector.model.debt.DebtDetail;

public interface IDebtService {

    @NotNull
    List<AggregateDebt> findAllByUserId(@Nullable final String userId);

    @NotNull
    List<DebtDetail> findByDebtorIdAndUserId(@Nullable final String debtorId, @Nullable final String userId) throws EntityNotFoundException;

    @Nullable
    Debt updateByIdAndUserId(@Nullable final DebtDTO debt, @NotNull final String id, @Nullable final String userId)
            throws EntityNotFoundException, InvalidEntityParamentException;

    @NotNull
    List<Debt> findAllByUserIdAndStatus(@Nullable final String userId, @Nullable final String... status);

    @NotNull
    Debt create(@Nullable final DebtDTO debt, @Nullable final String userId) throws InvalidEntityParamentException;

    void deleteByIdAndUserId(@Nullable final String id, @Nullable final String userId) throws EntityNotFoundException;

}
