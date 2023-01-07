package ru.petcollector.petcollector.service;

import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import ru.petcollector.petcollector.repository.LoginUserRepository;

@Component
public class UserAuthDetailService implements UserDetailsService {

    @NotNull
    private LoginUserRepository repository;

    public UserAuthDetailService(@NotNull final LoginUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(@NotNull final String username) throws UsernameNotFoundException {
        return repository.findByLogin(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }

}
