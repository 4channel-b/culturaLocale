package com.fourchannel.b.culturaLocale.repositories;

import com.fourchannel.b.culturaLocale.dataModels.Event;

import java.util.Vector;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface IRepository<T> {
    T save(T item);
    T findById(String id);
    List<T> findAll();
    void update(T item);
    void delete(T item);
}