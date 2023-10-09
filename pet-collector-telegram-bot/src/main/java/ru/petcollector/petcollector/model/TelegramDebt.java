package ru.petcollector.petcollector.model;

import java.io.Serializable;

import org.jetbrains.annotations.Nullable;

public class TelegramDebt implements Serializable {

    @Nullable
    private String id;

    @Nullable
    private String ownerId;

    @Nullable
    private String debtorId;

    @Nullable
    private Float sum;

    @Nullable
    private String description;
    
}
