package ru.petcollector.petcollector.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import ru.petcollector.petcollector.model.Debt;

@Repository
public interface DebtRepository extends MongoRepository<Debt, String> {
    
}
