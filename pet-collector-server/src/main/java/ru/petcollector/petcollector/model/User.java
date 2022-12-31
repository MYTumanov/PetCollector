package ru.petcollector.petcollector.model;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Document("users")
@NoArgsConstructor
public class User {

    @Id
    @NotNull
    @Field(targetType = FieldType.OBJECT_ID)
    private String id = new ObjectId().toHexString();

    @NotNull
    @Version
    private Integer version;

    @NotNull
    @CreatedDate
    private Date created;

    @NotNull
    @LastModifiedDate
    private Date lastUpdate;

    @NotNull
    private String lastName;

    @NotNull
    private String firsName;

    @NotNull
    private String midleName;

    @NotNull
    @Indexed(unique = true)
    private String login;

    @Nullable
    private String password;

    @NotNull
    @ReadOnlyProperty
    @DocumentReference(lookup = "{'debtorId':?#{#self._id} }")
    private List<Debt> reqDebts;

    @NotNull
    @ReadOnlyProperty
    @DocumentReference(lookup = "{'ownerId':?#{#self._id} }")
    private List<Debt> ownDebts;
    
}
