package ru.petcollector.petcollector.map;

import org.jetbrains.annotations.NotNull;

import ru.petcollector.petcollector.model.Debt;
import ru.petcollector.petcollector.model.DebtDTO;

public interface DebtMapper {
    
    @NotNull
    public static Debt map(@NotNull final DebtDTO debtDTO) {
        debtDTO.setId(null);
        return map(debtDTO, new Debt());
    }

    @NotNull
    public static Debt map(@NotNull final DebtDTO debtDTO, @NotNull final Debt debt) {
        debt.setId(debtDTO.getId() != null ? debtDTO.getId() : debt.getId());
        debt.setSum(debtDTO.getSum() != null ? debtDTO.getSum() : debt.getSum());
        debt.setOwnerId(debtDTO.getOwnerId() != null ? debtDTO.getOwnerId() : debt.getOwnerId());
        debt.setDebtorId(debtDTO.getDebtorId() != null ? debtDTO.getDebtorId() : debt.getDebtorId());
        return debt;
    }
}
