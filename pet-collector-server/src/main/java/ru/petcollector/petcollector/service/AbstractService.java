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
    protected R repository;

    @Nullable
    public M findById(@NotNull final String id) {
        @NotNull
        Optional<M> modelOpt = repository.findById(id);
        if (!modelOpt.isPresent()) {
            return null;
        }
        return modelOpt.get();
    }

    @Nullable
    public List<M> findAll() {
        return repository.findAll();
    }

    @NotNull
    public M merge(@NotNull final M model) throws IllegalArgumentException {
        return repository.save(model);
    }

    @NotNull
    public List<M> createAll(@NotNull final List<M> models) throws IllegalArgumentException {
        return repository.saveAll(models);
    }

    public void deleteById(@Nullable final String id) throws IllegalArgumentException {
        if (id == null)
            throw new IllegalArgumentException("id is null");
        @NotNull
        Optional<M> modelOpt = repository.findById(id);
        if (!modelOpt.isPresent()) {
            throw new IllegalArgumentException("Entity not found by id: " + id);
        }
        modelOpt.stream().forEach(m -> m.setDeleted(true));
        repository.save(modelOpt.get());
    }

    public long getSize() {
        return repository.count();
    }

}
