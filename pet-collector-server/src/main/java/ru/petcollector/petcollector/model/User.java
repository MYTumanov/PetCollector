package ru.petcollector.petcollector.model;

import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.petcollector.petcollector.exception.AbstractPetCollectorException;
import ru.petcollector.petcollector.exception.EntityCastException;

@Getter
@Setter
@Document("users")
@NoArgsConstructor
public class User extends AbstractModel {

    private void mapEntity(@NotNull final User user) {
        this.firsName = user.firsName;
        this.lastName = user.lastName;
        this.midleName = user.midleName;
        this.login = user.login;
        this.password = user.password;
    }

    @Override
    public <T extends AbstractModel> void mapEntity(@NotNull final T model) throws AbstractPetCollectorException {
        if (model instanceof User) {
            mapEntity((User) model);
        } else {
            throw new EntityCastException();
        }  
    }

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
    @JsonIgnore
    private String password;

    @Nullable
    @ReadOnlyProperty
    @DocumentReference(lookup = "{'debtorId':?#{#self._id} }")
    private List<Debt> reqDebts;

    @Nullable
    @ReadOnlyProperty
    @DocumentReference(lookup = "{'ownerId':?#{#self._id} }")
    private List<Debt> ownDebts;

}
