package ru.petcollector.petcollector.model.debtor;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.ReadOnlyProperty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import ru.petcollector.petcollector.enumerated.DebtStatus;
import ru.petcollector.petcollector.exception.InvalidEntityParamentException;
import ru.petcollector.petcollector.model.AbstractModel;
import ru.petcollector.petcollector.model.user.User;

@Getter
@Setter
@Entity
@Table(name = "debtor")
public class Debtor extends AbstractModel {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotNull
    private String userId;

    @NotNull
    private String debtId;

    @NotNull
    private Float sum = 0f;

    @NotNull
    @Enumerated(EnumType.STRING)
    private DebtStatus status = DebtStatus.IN_PROCESS;

    @NotNull
    @OneToOne
    @JsonIgnore
    @ReadOnlyProperty
    @Getter(AccessLevel.NONE)
    @JoinColumn(name = "userId", referencedColumnName = "id", insertable = false, updatable = false)
    private User user;

    @NotNull
    @ReadOnlyProperty
    public String getFullName() throws InvalidEntityParamentException {
        if (user == null)
            throw new InvalidEntityParamentException();
        return (user.getTelegramUser().getTelegramUserName());
    }

}
