package ru.petcollector.petcollector.service;

import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Service;

import ru.petcollector.petcollector.exception.AbstractPetCollectorException;
import ru.petcollector.petcollector.exception.EntityNotFoundException;
import ru.petcollector.petcollector.map.DebtMapper;
import ru.petcollector.petcollector.model.Debt;
import ru.petcollector.petcollector.model.DebtDTO;
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

    @NotNull
    public Debt create(@NotNull final DebtDTO userDTO) throws IllegalArgumentException {
        return repository.save(DebtMapper.map(userDTO));
    }

    @NotNull
    public Debt update(@NotNull final DebtDTO debtDTO) throws AbstractPetCollectorException {
        @Nullable
        final Debt debt = findById(debtDTO.getId());
        if (debt == null) {
            throw new EntityNotFoundException("Entity is not found with id: " + debtDTO.getId());
        }
        return repository.save(DebtMapper.map(debtDTO, debt));
    }

}
