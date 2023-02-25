package ru.petcollector.petcollector.model.debtor;

import java.util.Optional;

import lombok.Getter;
import lombok.Setter;
import ru.petcollector.petcollector.enumerated.DebtStatus;

@Getter
@Setter
public class DebtorDTO {

    private Optional<String> id;

    private Optional<String> userId;

    private Optional<Float> sum;

    private Optional<String> phoneNumber;

    private Optional<DebtStatus> status;

}
