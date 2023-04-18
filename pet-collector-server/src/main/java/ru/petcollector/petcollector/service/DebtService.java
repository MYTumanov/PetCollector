package ru.petcollector.petcollector.service;

import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Service;

import ru.petcollector.petcollector.api.service.IDebtService;
import ru.petcollector.petcollector.enumerated.DebtStatus;
import ru.petcollector.petcollector.exception.EntityNotFoundException;
import ru.petcollector.petcollector.exception.InvalidEntityParamentException;
import ru.petcollector.petcollector.map.DebtMapper;
import ru.petcollector.petcollector.model.debt.AggregateDebt;
import ru.petcollector.petcollector.model.debt.Debt;
import ru.petcollector.petcollector.model.debt.DebtDTO;
import ru.petcollector.petcollector.repository.DebtRepository;

@Service
public class DebtService extends AbstractService<Debt, DebtRepository> implements IDebtService {

    @Override
    @NotNull
    public List<AggregateDebt> findAllByUserId(@Nullable final String userId) {
        if (userId == null || userId.isEmpty())
            throw new IllegalArgumentException();
        return repository.findAllByDebtorUserId(userId);
    }

    @Override
    @NotNull
    public Debt findByIdAndUserId(@Nullable final String id, @Nullable final String userId)
            throws EntityNotFoundException {
        if (userId == null || userId.isEmpty())
            throw new IllegalArgumentException();
        if (id == null || id.isEmpty())
            throw new IllegalArgumentException();
        return repository.findByIdAndUserId(id, userId).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    @NotNull
    public List<Debt> findAllByUserIdAndStatus(@Nullable final String userId, @Nullable final String... statuses) {
        if (userId == null || userId.isEmpty())
            throw new IllegalArgumentException();
        if (statuses == null || statuses.length == 0)
            throw new IllegalArgumentException();
        DebtStatus[] debtStatuses = new DebtStatus[statuses.length];
        for (int i = 0; i < statuses.length; i++)
            debtStatuses[i] = DebtStatus.toStatus(statuses[i]);
        return repository.findAllByUserIdAndStatuses(userId, debtStatuses);
    }

    @Override
    @Nullable
    public Debt updateByIdAndUserId(
            @Nullable final DebtDTO debtDTO,
            @NotNull final String id,
            @Nullable final String userId) throws EntityNotFoundException, InvalidEntityParamentException {
        if (userId == null || userId.isEmpty())
            throw new IllegalArgumentException();
        if (debtDTO == null)
            throw new IllegalArgumentException();
        @NotNull
        final Debt debt = DebtMapper.map(debtDTO,
                repository.findByIdAndUserId(id, userId).orElseThrow(EntityNotFoundException::new));
        debt.setId(id);
        debt.setOwnerId(userId);
        return repository.save(debt);
    }

    @Override
    @NotNull
    public Debt create(@Nullable final DebtDTO debtDTO, @Nullable final String userId)
            throws InvalidEntityParamentException {
        if (userId == null || userId.isEmpty())
            throw new IllegalArgumentException();
        if (debtDTO == null)
            throw new IllegalArgumentException();
        @NotNull
        final Debt debt = DebtMapper.map(debtDTO, new Debt());
        debt.setOwnerId(userId);
        debt.setVersion(0d);
        return repository.save(debt);
    }

    @Override
    public void deleteByIdAndUserId(@Nullable final String id, @Nullable final String userId)
            throws EntityNotFoundException {
        if (userId == null || userId.isEmpty())
            throw new IllegalArgumentException();
        if (id == null || id.isEmpty())
            throw new IllegalArgumentException();
        @NotNull
        final Debt debt = repository.findByIdAndUserId(id, userId).orElseThrow(EntityNotFoundException::new);
        debt.setDeleted(true);
        repository.save(debt);
    }

}
