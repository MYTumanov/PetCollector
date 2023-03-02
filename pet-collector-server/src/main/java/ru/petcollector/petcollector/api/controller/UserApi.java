package ru.petcollector.petcollector.api.controller;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping
    public ResponseEntity<User> getUser(@RequestParam("userId") @NotNull final String userId) {
        try {
            return ResponseEntity.ok(service.findById(userId));
        } catch (@NotNull final EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (@NotNull final Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/byPhone")
    public ResponseEntity<String> getUserByPhone(@RequestParam("userId") @NotNull final String userId, @RequestParam("phone") @NotNull final String phone) {
        try {
            @Nullable String response = null;
            @Nullable final User user = service.findByPhoneNumber(phone);
            if (user != null) {
                response = user.getId();
            }
            return ResponseEntity.ok(response);
        } catch (@NotNull final Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/exists")
    public ResponseEntity<Boolean> existsByLogin(@RequestParam("login") @NotNull final String login) {
        try {
            return ResponseEntity.ok(service.existsByLogin(login));
        } catch (@NotNull final Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> updateUser(@RequestParam("userId") @NotNull final String userId, @RequestBody UserDTO userDTO) {
        try {
            return ResponseEntity.ok(service.updateById(userId, userDTO));
        } catch (@NotNull final EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (@NotNull final Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<User> deleteUser(@RequestParam("userId") @NotNull final String userId) {
        try {
            service.deleteById(userId);
            return ResponseEntity.ok().build();
        } catch (@NotNull final EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (@NotNull final Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

}
