package com.rathercruel.forum.dao;

import java.util.Optional;

public interface DAO<T> {
    Optional<T> get(Long id);
    T save(T t);
    T update(T t);
    void delete(T t);
}
