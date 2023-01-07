package ru.petcollector.petcollector.api.controller;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.petcollector.petcollector.model.LoginRequest;
import ru.petcollector.petcollector.model.LoginResponse;
import ru.petcollector.petcollector.model.LoginUser;
import ru.petcollector.petcollector.service.JWTTokenProviderService;

@RestController
@RequestMapping("/api/authenticate")
public class AuthApi {

    @NotNull
    @Autowired
    private AuthenticationManager authenticationManager;

    @NotNull
    @Autowired
    private JWTTokenProviderService jwtTokenProviderService;

    @PostMapping
    public ResponseEntity<LoginResponse> authenticateUser(@RequestBody @NotNull final LoginRequest request) {
        @NotNull
        final Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getLogin(), request.getPassword()));

        @NotNull
        final String token = jwtTokenProviderService.generateToken((LoginUser) authentication.getPrincipal());
        return ResponseEntity.ok(new LoginResponse(token));
    }

}
