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

    private Optional<Float> totalSum = Optional.empty();

    private Optional<String> comment = Optional.empty();

    private Optional<DebtStatus> status = Optional.empty();

    private Optional<List<DebtorDTO>> debtors = Optional.empty();

    private Optional<Double> version = Optional.empty();

}
