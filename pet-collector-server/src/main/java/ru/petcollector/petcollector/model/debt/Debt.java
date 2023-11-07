package ru.petcollector.petcollector.model.debt;

import java.util.ArrayList;
import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.annotation.ReadOnlyProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import ru.petcollector.petcollector.enumerated.DebtStatus;
import ru.petcollector.petcollector.model.AbstractModel;
import ru.petcollector.petcollector.model.debtor.Debtor;

@Getter
@Setter
@Entity
@Table(name = "debt")
public class Debt extends AbstractModel {

    @Nullable
    private String comment;

    @NotNull
    private String ownerId;

    @NotNull
    @JoinColumn(name = "debtId")
    @OneToMany(cascade = CascadeType.ALL)
    private List<Debtor> debtors = new ArrayList<>();

    @NotNull
    @ReadOnlyProperty
    public Float getTotalSum() {
        Float sum = 0f;
        for (Debtor debtor : debtors) {
            sum += debtor.getSum();
        }
        return sum;
    }

    @Nullable
    @ReadOnlyProperty
    public DebtStatus getStatus() {
        for (Debtor debtor : debtors) {
            if (debtor.getStatus() == DebtStatus.IN_PROCESS) {
                return DebtStatus.IN_PROCESS;
            } else {
                return DebtStatus.PAID;
            }
        }
        return null;
    }

}
