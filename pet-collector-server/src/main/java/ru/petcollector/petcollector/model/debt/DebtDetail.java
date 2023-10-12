package ru.petcollector.petcollector.model.debt;

import java.util.Date;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
import ru.petcollector.petcollector.enumerated.DebtStatus;

@Getter
@Setter
public class DebtDetail {

    @NotNull
    private String id;

    @NotNull
    private Float sum;

    @NotNull
    private DebtStatus status;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date created;

    @NotNull
    private String ownerId;

    @NotNull
    private String debtorId;

    @Nullable
    private String comment;

}
