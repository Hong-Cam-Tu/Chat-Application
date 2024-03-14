package com.chatapplication.infrastructure.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import com.chatapplication.domains.BaseEntity;
import com.chatapplication.usecases.adapters.Repository;

public class InMemoryRepository<T extends BaseEntity> implements Repository<T> {
    private List<T> _enities;
    public static int idCounter = 1;

    public InMemoryRepository() {
        _enities = new ArrayList<>();
    }

    @Override
    public T getById(String id) {
        Optional<T> entity = _enities.stream().filter(e -> e.getID().equals(id)).findFirst();
        return entity.get();
    }

    @Override
    public boolean add(T entity) {

        if (entity == null) {
            return false;
        }

        _enities.add(entity);
        return true;
    }

    @Override
    public void deleteAll() {
        _enities.clear();
    }

    @Override
    public T getFirst(Predicate<T> predicate) {
        Optional<T> entity = _enities.stream().filter(predicate).findFirst();
        return entity.isPresent() ? entity.get() : null;
    }

    @Override
    public List<T> getAll(Predicate<T> predicate) {
        List<T> entity = _enities.stream().filter(predicate).toList();
        return entity;
    }

    @Override
    public boolean remove(T removeEntity) {
        if(removeEntity == null) {
            return false;
        }
        _enities.remove(removeEntity);
        return true;
    }

}
