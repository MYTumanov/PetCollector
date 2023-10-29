package ru.petcollector.petcollector.repository;

import java.util.List;
import java.util.Optional;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import ru.petcollector.petcollector.enumerated.DebtStatus;
import ru.petcollector.petcollector.model.debt.AggregateDebt;
import ru.petcollector.petcollector.model.debt.Debt;
import ru.petcollector.petcollector.model.debt.DebtDetail;

public interface DebtRepository extends AbstractRepository<Debt> {

    // Find all debts where userId is owner or participant.
    // Sorted by created desc.
    // @Nullable
    // public List<AggregateDebt> findAllByDebtorUserId(@NotNull final String userId);

    // Find debt by id where userId owner or participant.
    @NotNull
    public Optional<Debt> findByIdAndOwnerId(@NotNull final String id, @NotNull final String ownerId);

    // @NotNull
    // public Optional<List<DebtDetail>> findByDebtorIdAndUserId(@NotNull final String debtorId, @NotNull final String userId);

    // // Find all debts where userId is owner or participants
    // // and statuses are equal to given.
    // // Sorted by created desc.
    // @Nullable
    // public List<Debt> findAllByUserIdAndStatuses(@NotNull final String userId,
    //         @NotNull final DebtStatus... statuses);
}
