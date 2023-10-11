package ru.petcollector.petcollector.model;

import java.io.Serializable;

import org.jetbrains.annotations.Nullable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TelegramDebtor implements Serializable {

    @Nullable
    private String id;

    @Nullable
    private String userId;

    @Nullable
    private Float sum;

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", userId='" + getUserId() + "'" +
                ", sum='" + getSum() + "'" +
                "}";
    }

}
