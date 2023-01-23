package ru.petcollector.petcollector.model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import ru.petcollector.petcollector.enumerated.DebtStatus;

@Getter
@Setter
public class Debtor {

    @Nullable
    @Field(targetType = FieldType.OBJECT_ID)
    private String userId;

    @NotNull
    private boolean isOwner;

    @NotNull
    private Float sum;

    @Nullable
    private String phoneNumber;

    @NotNull
    private DebtStatus status;

    @NotNull
    @JsonIgnore
    @ReadOnlyProperty
    @Getter(AccessLevel.NONE)
    @DocumentReference(lookup = "{'self._id':?#{#userId} }")
    private User user;

    @NotNull
    public String getFullName() {
        if (user == null) 
            return "";
        return (user.getLastName() + " " + user.getFirstName() + " " + user.getMidleName()).trim();
    }

}
