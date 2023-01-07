package ru.petcollector.petcollector.service;

import java.util.Date;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import ru.petcollector.petcollector.model.LoginUser;

@Slf4j
@Component
@PropertySource("classpath:application.properties")
public class JWTTokenProviderService {

    @NotNull
    @Value("${jwt.secret}")
    private String jwtSecret;

    @NotNull
    @Value("${jwt.expirationInMs}")
    private int jwtExpirationInMs;

    @NotNull
    public String generateToken(@NotNull final LoginUser user) {
        return Jwts.builder().setIssuer("pet-collector-authenticator").setSubject(user.getLogin())
                .setIssuedAt(new Date()).setExpiration(new Date(new Date().getTime() + jwtExpirationInMs * 10000))
                .signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
    }

    public boolean validateToken(@NotNull final String jwt) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwt);
            return true;
        } catch (@NotNull final Exception e) {
            log.error(e.toString(), e);
        }
        return false;
    }

}
