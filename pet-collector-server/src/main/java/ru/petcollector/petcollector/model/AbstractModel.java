package ru.petcollector.petcollector.model;

import java.util.Date;

import org.bson.types.ObjectId;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import ru.petcollector.petcollector.exception.AbstractPetCollectorException;

@Getter
@Setter
public abstract class AbstractModel {

    @Id
    @NotNull
    @Field(targetType = FieldType.OBJECT_ID)
    private String id = new ObjectId().toHexString();

    @NotNull
    @Version
    @JsonIgnore
    private Integer version;

    @NotNull
    @CreatedDate
    @JsonIgnore
    private Date created;

    @NotNull
    @LastModifiedDate
    @JsonIgnore
    private Date lastUpdate;

    @JsonIgnore
    private boolean isDeleted = false;

    public abstract <T extends AbstractModel> void mapEntity(@NotNull final T model) throws AbstractPetCollectorException;

}
