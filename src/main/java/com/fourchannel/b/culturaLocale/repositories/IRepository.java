package com.fourchannel.b.culturaLocale.repositories;

import java.util.List;

public interface IRepository<T> {
    T save(T item);
    T findById(String id);
    List<T> findAll();
    void update(T item);
    void delete(T item);
}