package ru.petcollector.petcollector.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import ru.petcollector.petcollector.model.AbstractModel;

public interface AbstractRepository<M extends AbstractModel> extends MongoRepository<M, String> {
    
}
