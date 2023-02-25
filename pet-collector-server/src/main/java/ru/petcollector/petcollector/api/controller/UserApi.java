package ru.petcollector.petcollector.api.controller;

import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.petcollector.petcollector.exception.EntityNotFoundException;
import ru.petcollector.petcollector.model.user.User;
import ru.petcollector.petcollector.model.user.UserDTO;
import ru.petcollector.petcollector.service.UserService;

@RestController
@RequestMapping(path = "api/user")
public class UserApi {

    @NotNull
    @Autowired
    private UserService service;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable String id) {
        try {
            @Nullable
            final User user = service.findById(id);
            return ResponseEntity.ok(user);
        } catch (@NotNull final EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (@NotNull final Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<User>> getUserList() {
        try {
            return ResponseEntity.ok(service.findAll());
        } catch (@NotNull final Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> createUser(@RequestBody UserDTO userDTO) {
        try {
            return ResponseEntity.ok(service.create(userDTO));
        } catch (@NotNull final Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> updateUser(@RequestBody UserDTO userDTO) {
        try {
            return ResponseEntity.ok(service.update(userDTO));
        } catch (@NotNull final EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (@NotNull final Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable String id) {
        try {
            service.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (@NotNull final EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (@NotNull final Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
