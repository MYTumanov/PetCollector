package ru.petcollector.petcollector.repository;

import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Repository;

import ru.petcollector.petcollector.model.Debt;

@Repository
public interface DebtRepository extends AbstractRepository<Debt> {

    @Nullable
    public List<Debt> findAllByDebtorId(@NotNull String debtorId);

    @Nullable
    public List<Debt> findAllByOwnerId(@NotNull String ownerId);

    public boolean existsByDebtorId(@NotNull String debtorId);

    public boolean existsByOwnerId(@NotNull String ownerId);

}
