package ru.petcollector.petcollector.model;

import java.util.ArrayList;
import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document("debts")
public class Debt extends AbstractModel {

    // @NotNull
    // @Field(targetType = FieldType.OBJECT_ID)
    // private String ownerId;

    // @NotNull
    // @Field(targetType = FieldType.OBJECT_ID)
    // private String debtorId;

    @NotNull
    private Float totalSum;

    @NotNull
    private List<Debtor> ownDebts = new ArrayList<>();

}
