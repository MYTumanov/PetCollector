package ru.petcollector.petcollector.service;

import java.util.List;
import java.util.Optional;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;

import ru.petcollector.petcollector.exception.EntityNotFoundException;
import ru.petcollector.petcollector.model.AbstractModel;
import ru.petcollector.petcollector.repository.AbstractRepository;

public abstract class AbstractService<M extends AbstractModel, R extends AbstractRepository<M>> {

    @NotNull
    @Autowired
    protected R repository;

    @NotNull
    public M findById(@NotNull final String id) throws EntityNotFoundException {
        @NotNull
        Optional<M> modelOpt = repository.findById(id);
        if (!modelOpt.isPresent()) {
            throw new EntityNotFoundException("Entity is not found by id: " + id);
        }
        return modelOpt.get();
    }

    @Nullable
    public List<M> findAll() {
        return repository.findAll();
    }

    @NotNull
    public List<M> createAll(@NotNull final List<M> models) throws IllegalArgumentException {
        return repository.saveAll(models);
    }

    public void deleteById(@Nullable final String id) throws EntityNotFoundException, IllegalArgumentException {
        if (id == null || id.isEmpty())
            throw new IllegalArgumentException("id");
        @NotNull
        final M model = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        model.setDeleted(true);
        repository.save(model);
    }

    public long getSize() {
        return repository.count();
    }

}
