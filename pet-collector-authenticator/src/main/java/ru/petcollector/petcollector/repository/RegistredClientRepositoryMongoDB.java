package ru.petcollector.petcollector.repository;

import java.util.Optional;
import java.util.stream.Collectors;

import org.jetbrains.annotations.NotNull;
import org.springframework.lang.Nullable;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Repository;

import ru.petcollector.petcollector.model.Client;

@Repository
public class RegistredClientRepositoryMongoDB implements RegisteredClientRepository {

    @NotNull
    private ClientRepository repository;

    public RegistredClientRepositoryMongoDB(@NotNull final ClientRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(RegisteredClient registeredClient) {
        @NotNull
        final Client client = new Client();
        client.setClientId(registeredClient.getClientId());
        client.setClientSecrete(registeredClient.getClientSecret());
        client.setAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC.getValue());
        client.setAuthorizationTypes(registeredClient.getAuthorizationGrantTypes().stream().map(AuthorizationGrantType::getValue).collect(Collectors.toSet()));
        client.setScopes(registeredClient.getScopes());
        client.setRedirectUri(registeredClient.getRedirectUris().stream().findFirst().orElse(null));
        repository.save(client);
    }

    @Override
    @Nullable
    public RegisteredClient findById(String id) {
        @NotNull
        Optional<Client> optionalClient = repository.findById(id);
        if (!optionalClient.isPresent())
            return null;
        @NotNull
        Client client = optionalClient.get();
        return RegisteredClient.withId(client.getId()).clientId(client.getClientId())
                .clientSecret(client.getClientSecrete())
                .redirectUri(client.getRedirectUri())
                .clientAuthenticationMethod(new ClientAuthenticationMethod(client.getAuthenticationMethod()))
                .authorizationGrantTypes(set -> set.addAll(client.getAuthorizationTypes().stream()
                        .map(AuthorizationGrantType::new).collect(Collectors.toSet())))
                .scopes(set -> set.addAll(client.getScopes())).build();
    }

    @Override
    @Nullable
    public RegisteredClient findByClientId(String clientId) {
        @NotNull
        Optional<Client> optionalClient = repository.findByClientId(clientId);
        if (!optionalClient.isPresent())
            return null;
        @NotNull
        Client client = optionalClient.get();
        return RegisteredClient.withId(client.getId()).clientId(client.getClientId())
                .clientSecret(client.getClientSecrete())
                .redirectUri(client.getRedirectUri())
                .clientAuthenticationMethod(new ClientAuthenticationMethod(client.getAuthenticationMethod()))
                .authorizationGrantTypes(set -> set.addAll(client.getAuthorizationTypes().stream()
                        .map(AuthorizationGrantType::new).collect(Collectors.toSet())))
                .scopes(set -> set.addAll(client.getScopes())).build();
    }

}
