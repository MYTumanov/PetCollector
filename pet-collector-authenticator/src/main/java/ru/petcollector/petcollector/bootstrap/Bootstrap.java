package ru.petcollector.petcollector.bootstrap;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.stereotype.Component;

import lombok.extern.java.Log;
import ru.petcollector.petcollector.repository.RegistredClientRepositoryMongoDB;

@Component
@Log
public class Bootstrap {

    @NotNull
    @Autowired
    private RegistredClientRepositoryMongoDB repository;

    public void initClient() {
        try {
            @NotNull
            final RegisteredClient registeredClient = RegisteredClient.withId("123").clientId("krakend-client")
                    .clientSecret("{noop}secrete").clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                    .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                    .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE).scope("read").scope("write")
                    .redirectUri("http://127.0.0.1:8090/oauth2/authorized").build();

            log.info(registeredClient.toString());
            repository.save(registeredClient);
        } catch (@NotNull final Exception e) {
            e.printStackTrace();
        }
    }

}
