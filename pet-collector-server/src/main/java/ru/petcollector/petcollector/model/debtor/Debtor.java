package ru.petcollector.petcollector.model.debtor;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.ReadOnlyProperty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import ru.petcollector.petcollector.enumerated.DebtStatus;
import ru.petcollector.petcollector.exception.InvalidEntityParamentException;
import ru.petcollector.petcollector.model.user.User;

@Getter
@Setter
@Entity
@Table(name = "debtor")
public class Debtor {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotNull
    private Float sum = 0f;

    @NotNull
    private DebtStatus status = DebtStatus.IN_PROCCESS;

    @NotNull
    @ManyToOne
    @JsonIgnore
    @ReadOnlyProperty
    @Getter(AccessLevel.NONE)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @NotNull
    @ReadOnlyProperty
    public String getFullName() throws InvalidEntityParamentException {
        if (user == null)
            throw new InvalidEntityParamentException();
        return (user.getTelegramUser().getTelegramUserName());
    }

}
