package ru.petcollector.petcollector.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.fasterxml.jackson.annotation.JsonFormat;

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
    private String status;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date created;

    @Nullable
    private List<TelegramDebtor> debtors = new ArrayList<>();

    public String getStatusEmoji(final String id) {
        return ownerId.equals(id) ? ":money_mouth:" : ":fearful:";
    }

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
