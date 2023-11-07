package ru.petcollector.petcollector.repository;

import java.util.List;
import java.util.Optional;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ru.petcollector.petcollector.model.debt.AggregateDebt;
import ru.petcollector.petcollector.model.debt.Debt;

public interface DebtRepository extends AbstractRepository<Debt> {

    // Find debt by id where userId owner or participant.
    @NotNull
    public Optional<Debt> findByIdAndOwnerId(@NotNull final String id, @NotNull final String ownerId);

    @NotNull
    @Query("SELECT d FROM Debt d JOIN FETCH d.debtors dr \r\n" +
            "WHERE (d.ownerId = :userId AND dr.userId = :debtorId) OR (d.ownerId = :debtorId AND dr.userId=:userId) \r\n" +
            "ORDER BY d.lastUpdate")
    public Optional<List<Debt>> findByOwnerIdAnddebtorId(@NotNull @Param("debtorId") final String debtorId,
            @NotNull @Param("userId") final String userId);

    @Query(name = "AggregateDebt")
    public List<AggregateDebt> findAllByDebtorUserId(@NotNull @Param("userId") final String userId);

}