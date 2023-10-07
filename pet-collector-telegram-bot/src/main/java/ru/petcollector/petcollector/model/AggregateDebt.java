package ru.petcollector.petcollector.model;

import java.util.Date;

import org.jetbrains.annotations.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

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
    private String name;

    @Override
    public String toString() {
        return "Сумма: '" + getTotalDebt() + "'" +
                ", Имя: '" + getName() + "'";
    }

}
