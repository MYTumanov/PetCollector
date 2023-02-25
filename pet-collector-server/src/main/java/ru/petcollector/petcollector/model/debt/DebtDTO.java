package ru.petcollector.petcollector.model.debt;

import java.util.List;
import java.util.Optional;

import lombok.Getter;
import lombok.Setter;
import ru.petcollector.petcollector.enumerated.DebtStatus;
import ru.petcollector.petcollector.model.debtor.DebtorDTO;

@Getter
@Setter
public class DebtDTO {

    private Optional<Float> totalSum;

    private Optional<String> comment;

    private Optional<DebtStatus> status;

    private Optional<List<DebtorDTO>> debtors;

}
