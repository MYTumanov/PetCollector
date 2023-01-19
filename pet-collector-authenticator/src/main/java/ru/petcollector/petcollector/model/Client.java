package ru.petcollector.petcollector.model;

import java.util.Date;
import java.util.Set;

import org.bson.types.ObjectId;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Document("clients")
public class Client {

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

    @NotNull
    private String clientId;

    @NotNull
    private String clientSecrete;

    @NotNull
    private String authenticationMethod;

    @Nullable
    private Set<String> authorizationTypes;

    @Nullable
    private Set<String> scopes;

    @Nullable
    private String redirectUri;

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", version='" + getVersion() + "'" +
            ", created='" + getCreated() + "'" +
            ", lastUpdate='" + getLastUpdate() + "'" +
            ", isDeleted='" + isDeleted() + "'" +
            ", clientId='" + getClientId() + "'" +
            ", clientSecrete='" + getClientSecrete() + "'" +
            ", authenticationMethod='" + getAuthenticationMethod() + "'" +
            ", authorizationTypes='" + getAuthorizationTypes() + "'" +
            ", scopes='" + getScopes() + "'" +
            ", redirectUri='" + getRedirectUri() + "'" +
            "}";
    }
    
}
