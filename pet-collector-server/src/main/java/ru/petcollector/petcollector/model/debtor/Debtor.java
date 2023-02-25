package ru.petcollector.petcollector.model.debtor;

import org.bson.types.ObjectId;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import ru.petcollector.petcollector.enumerated.DebtStatus;
import ru.petcollector.petcollector.model.user.User;

@Getter
@Setter
public class Debtor {

    @Id
    @NotNull
    @Field(targetType = FieldType.OBJECT_ID)
    private String id = new ObjectId().toHexString();

    @NotNull
    @Field(targetType = FieldType.OBJECT_ID)
    private String userId = "";

    @NotNull
    private Float sum = 0f;

    @Nullable
    private String phoneNumber;

    @NotNull
    private DebtStatus status = DebtStatus.IN_PROCCESS;

    @NotNull
    @JsonIgnore
    @ReadOnlyProperty
    @Getter(AccessLevel.NONE)
    @DocumentReference(lookup = "{'_id':?#{#self.userId} }")
    private User user;

    @NotNull
    @ReadOnlyProperty
    public String getFullName() {
        if (user != null)
            return (user.getLastName() + " " + user.getFirstName() + " " + user.getMidleName()).trim();
        return phoneNumber;
    }

}
