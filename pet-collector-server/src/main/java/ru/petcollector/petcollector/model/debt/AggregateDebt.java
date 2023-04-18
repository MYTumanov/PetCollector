package ru.petcollector.petcollector.model.debt;

import java.util.Date;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import ru.petcollector.petcollector.model.user.User;

@Getter
@Setter
public class AggregateDebt {

    @NotNull
    private Float totalDebt;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastCreated;

    @NotNull
    @Field(name = "_id")
    private String userId;

    @NotNull
    @JsonIgnore
    @DocumentReference(lookup = "{'_id':?#{#self._id}}")
    private User user;

    @NotNull
    public String getName() {
        @NotNull
        final StringBuilder resultName = new StringBuilder();
        if (user.getLastName() != null && !user.getLastName().isEmpty())
            resultName.append(user.getLastName() + " ");
        if (user.getFirstName() != null && !user.getFirstName().isEmpty())
            resultName.append(user.getFirstName() + " ");
        if (user.getMidleName() != null && !user.getMidleName().isEmpty())
            resultName.append(user.getMidleName() + " ");
        if (resultName.isEmpty())
            resultName.append(user.getPhoneNumber());
        return resultName.toString().trim();
    }
}
