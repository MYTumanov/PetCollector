package ru.petcollector.petcollector.model;

import java.util.Date;

import org.bson.types.ObjectId;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document("debts")
public class Debt {

    @Id
    @NotNull
    @Field(targetType = FieldType.OBJECT_ID)
    private String id = new ObjectId().toHexString();

    @NotNull
    @Version
    private Integer version;

    @NotNull
    @Field(targetType = FieldType.OBJECT_ID)
    private String ownerId;

    @NotNull
    @Field(targetType = FieldType.OBJECT_ID)
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
