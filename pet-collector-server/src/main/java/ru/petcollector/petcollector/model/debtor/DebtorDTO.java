package ru.petcollector.petcollector.model.debtor;

import java.util.Optional;

import lombok.Getter;
import lombok.Setter;
import ru.petcollector.petcollector.enumerated.DebtStatus;

@Getter
@Setter
public class DebtorDTO {

    private Optional<String> id = Optional.empty();

    private Optional<String> userId = Optional.empty();

    private Optional<Float> sum = Optional.empty();

    private Optional<String> phoneNumber = Optional.empty();

    private Optional<DebtStatus> status = Optional.empty();

}
