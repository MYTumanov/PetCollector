package ru.petcollector.petcollector.model;

import java.util.Date;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractModel {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotNull
    @CreatedDate
    @JsonIgnore
    private Date created;

    @NotNull
    @LastModifiedDate
    @JsonIgnore
    private Date lastUpdate;

    @JsonIgnore
    private boolean deleted = false;

    @Version
    @Column(name = "rec_version")
    private Double version;

}
