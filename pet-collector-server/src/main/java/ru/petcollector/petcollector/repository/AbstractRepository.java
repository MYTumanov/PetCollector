package ru.petcollector.petcollector.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.petcollector.petcollector.model.AbstractModel;

public interface AbstractRepository<M extends AbstractModel> extends JpaRepository<M, String> {

}
