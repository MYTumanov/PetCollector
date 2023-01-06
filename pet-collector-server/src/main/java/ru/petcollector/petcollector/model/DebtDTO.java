package ru.petcollector.petcollector.model;

import org.jetbrains.annotations.Nullable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DebtDTO {

    @Nullable
    private String id;

    @Nullable
    private String ownerId;

    @Nullable
    private String debtorId;

    @Nullable
    private Float sum;
    
}
