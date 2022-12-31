package ru.petcollector.petcollector.service;

import java.util.List;
import java.util.Optional;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;

import ru.petcollector.petcollector.model.AbstractModel;
import ru.petcollector.petcollector.repository.AbstractRepository;

public abstract class AbstractService<M extends AbstractModel, R extends AbstractRepository<M>> {

    @NotNull
    @Autowired
    private R repository;

    @Nullable
    public M findById(@NotNull final String id) {
        @NotNull Optional<M> userOpt = repository.findById(id);
        if (!userOpt.isPresent()) {
            return null; 
        }
        return userOpt.get();
    }

    public List<M> findAll() {
        return repository.findAll();
    }
    
}
