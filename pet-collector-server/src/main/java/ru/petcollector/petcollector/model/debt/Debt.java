package ru.petcollector.petcollector.model.debt;

import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import lombok.Getter;
import lombok.Setter;
import ru.petcollector.petcollector.enumerated.DebtStatus;
import ru.petcollector.petcollector.model.AbstractModel;
import ru.petcollector.petcollector.model.debtor.Debtor;

@Getter
@Setter
@Document("debts")
public class Debt extends AbstractModel {

    @NotNull
    private Float sum;

    @Nullable
    private String comment;

    @NotNull
    @Field(targetType = FieldType.OBJECT_ID)
    private String ownerId;

    @NotNull
    private DebtStatus status = DebtStatus.IN_PROCCESS;

    @NotNull
    private List<Debtor> debtors = new ArrayList<>();

}
