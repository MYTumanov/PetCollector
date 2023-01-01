package ru.petcollector.petcollector.service;

import java.util.List;

import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Service;

import ru.petcollector.petcollector.model.Debt;
import ru.petcollector.petcollector.repository.DebtRepository;

@Service
public class DebtService extends AbstractService<Debt, DebtRepository> {

    @Nullable
    public List<Debt> findByDebtorId(@Nullable final String debtorId) throws IllegalArgumentException {
        if (debtorId == null)
            throw new IllegalArgumentException("debtorId is null");
        return repository.findAllByDebtorId(debtorId);
    }

    @Nullable
    public List<Debt> findByOwnerId(@Nullable final String ownerId) throws IllegalArgumentException {
        if (ownerId == null)
            throw new IllegalArgumentException("ownerId is null");
        return repository.findAllByDebtorId(ownerId);
    }

    public boolean existByDebtorId(@Nullable final String debtorId) throws IllegalArgumentException {
        if (debtorId == null)
            throw new IllegalArgumentException("debtorId is null");
        return repository.existsByDebtorId(debtorId);
    }

    public boolean existByOwnerId(@Nullable final String ownerId) throws IllegalArgumentException {
        if (ownerId == null)
            throw new IllegalArgumentException("ownerId is null");
        return repository.existsByDebtorId(ownerId);
    }

}
