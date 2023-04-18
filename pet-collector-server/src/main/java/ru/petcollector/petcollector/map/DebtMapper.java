package ru.petcollector.petcollector.map;

import java.util.HashMap;
import java.util.Map;

import org.jetbrains.annotations.NotNull;

import ru.petcollector.petcollector.exception.InvalidEntityParamentException;
import ru.petcollector.petcollector.model.debt.Debt;
import ru.petcollector.petcollector.model.debt.DebtDTO;
import ru.petcollector.petcollector.model.debtor.Debtor;
import ru.petcollector.petcollector.model.debtor.DebtorDTO;

public interface DebtMapper {

    @NotNull
    public static Debt map(@NotNull final DebtDTO debtDTO, @NotNull final Debt debt)
            throws InvalidEntityParamentException {
        debt.setSum(debtDTO.getSum().orElse(debt.getSum()));
        debt.setStatus(debtDTO.getStatus().orElse(debt.getStatus()));
        debt.setComment(debtDTO.getComment().orElse(debt.getComment()));
        debt.setVersion(debtDTO.getVersion().orElse(debt.getVersion()));

        if (debtDTO.getDebtors().isPresent()) {
            Map<String, Debtor> debtorsMap = new HashMap<>();
            for (Debtor debtor : debt.getDebtors())
                debtorsMap.put(debtor.getId(), debtor);

            for (DebtorDTO debtorDTO : debtDTO.getDebtors().get()) {
                Debtor debtor = debtorsMap.get(debtorDTO.getId().orElse(null));
                if (debtor != null) {
                    map(debtor, debtorDTO);
                } else {
                    debt.getDebtors().add(map(new Debtor(), debtorDTO));
                }
            }
        }

        return debt;
    }

    @NotNull
    private static Debtor map(@NotNull final Debtor to, @NotNull final DebtorDTO from)
            throws InvalidEntityParamentException {
        to.setStatus(from.getStatus().orElse(to.getStatus()));
        to.setPhoneNumber(from.getPhoneNumber().orElse(to.getPhoneNumber()));
        to.setSum(from.getSum().orElse(to.getSum()));
        if (to.getUserId().isEmpty()) {
            to.setUserId(from.getUserId().orElseThrow(InvalidEntityParamentException::new));
        }
        return to;
    }

}
