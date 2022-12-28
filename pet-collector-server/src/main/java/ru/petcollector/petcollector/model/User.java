package ru.petcollector.petcollector.model;

import org.bson.types.ObjectId;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
    private String id = new ObjectId().toHexString();

    @NotNull
    private String lastName;

    @NotNull
    private String firsName;

    @NotNull
    private String midleName;
    
}
