package ru.petcollector.petcollector.model.debt;

import java.util.Date;

import org.jetbrains.annotations.NotNull;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import ru.petcollector.petcollector.model.user.User;

@Getter
@Setter
public class AggregateDebt {

    @NotNull
    private Float totalDebt;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastCreated;

    @NotNull
    private String userId;

    @NotNull
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @NotNull
    public String getName() {
        return user.getTelegramUser().getTelegramUserName();
    }
}
