package ru.petcollector.petcollector.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.jetbrains.annotations.Nullable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TelegramDebt implements Serializable {

    @Nullable
    private String id;

    @Nullable
    private String ownerId;

    @Nullable
    private Float sum;

    @Nullable
    private String comment;

    @Nullable
    private List<TelegramDebtor> debtors = new ArrayList<>();

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", ownerId='" + getOwnerId() + "'" +
                ", sum='" + getSum() + "'" +
                ", comment='" + getComment() + "'" +
                ", debtors='" + getDebtors() + "'" +
                "}";
    }

}
