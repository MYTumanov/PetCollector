package ru.petcollector.petcollector.model;

import java.util.Date;

import org.bson.types.ObjectId;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document("debts")
public class Debt {

    @Id
    @NotNull
    private String id = new ObjectId().toHexString();

    @NotNull
    private String ownerId;

    @NotNull
    private String debtorId;

    @NotNull
    @CreatedDate
    private Date created;

    @NotNull
    @LastModifiedDate
    private Date lastUpdate;

    @NotNull
    private Float sum;
    
}
