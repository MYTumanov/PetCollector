package ru.petcollector.petcollector.model;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import lombok.Getter;
import lombok.Setter;
import ru.petcollector.petcollector.exception.AbstractPetCollectorException;
import ru.petcollector.petcollector.exception.EntityCastException;

@Getter
@Setter
@Document("debts")
public class Debt extends AbstractModel {

    private void mapEntity(@NotNull final Debt debt) {
        this.ownerId = debt.ownerId;
        this.debtorId = debt.debtorId;
        this.sum = debt.sum;
    }

    @Override
    public <T extends AbstractModel> void mapEntity(@NotNull final T model) throws AbstractPetCollectorException {
        if (model instanceof Debt) {
            mapEntity((Debt) model);
        } else {
            throw new EntityCastException();
        }  
    }

    @NotNull
    @Field(targetType = FieldType.OBJECT_ID)
    private String ownerId;

    @NotNull
    @Field(targetType = FieldType.OBJECT_ID)
    private String debtorId;

    @NotNull
    private Float sum;

}
