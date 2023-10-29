package ru.petcollector.petcollector.model.user;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users_web")
public class WebUser {

    private String lastName;

    private String firstName;

    private String midName;

    private String phoneNumber;

    private String login;

    private String password;

    @Id
    @OneToOne
    @JsonBackReference
    @JoinColumn(name = "par_id", referencedColumnName = "id")
    private User user;

}
