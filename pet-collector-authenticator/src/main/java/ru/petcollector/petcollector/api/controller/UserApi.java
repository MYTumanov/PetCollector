package ru.petcollector.petcollector.api.controller;

import java.util.Optional;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.petcollector.petcollector.interfaces.service.IUserService;
import ru.petcollector.petcollector.model.UserRegisterRequest;
import ru.petcollector.petcollector.model.UserRegisterResponse;

@RestController
@RequestMapping("/api/user")
public class UserApi {

    @Autowired
    private IUserService service;

    @PostMapping(path = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserRegisterResponse> userRegister(@RequestBody @Nullable final UserRegisterRequest user) {
        try {
            service.registerUser(user);
        } catch (@NotNull final Exception e) {
            @NotNull
            final UserRegisterResponse rs = new UserRegisterResponse();
            rs.setErrCode(1);
            rs.setErrMessage(e.getMessage());
            e.printStackTrace();
            return ResponseEntity.of(Optional.of(rs));
        }
        @NotNull
        final UserRegisterResponse rs = new UserRegisterResponse();
        rs.setErrCode(0);
        rs.setErrMessage(null);
        return ResponseEntity.of(Optional.of(rs));
    }

}
