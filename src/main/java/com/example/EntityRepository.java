package com.example;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public abstract class EntityRepository implements CrudRepository<Entity, String> {
}
