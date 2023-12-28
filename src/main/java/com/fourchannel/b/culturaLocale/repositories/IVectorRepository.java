package com.fourchannel.b.culturaLocale.repositories;

import java.util.List;
import java.util.Optional;
import java.util.Vector;

public class IVectorRepository<T> implements IRepository<T> {
    private final Vector<T> vector = new Vector<>();

    // Method to retrieve the ID from a generic object
    private String getIdFromObject(T item) {
        // Assuming item has a getID method that returns a String
        try {
            return (String) item.getClass().getMethod("getID").invoke(item);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error accessing getID method from object", e);
        }
    }

    @Override
    public void add(T item) {
        vector.add(item);
    }

    @Override
    public T findById(String id) {
        return vector.stream()
                .filter(item -> getIdFromObject(item).equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<T> findAll() {
        return new Vector<>(vector); // Return a copy of the Vector to avoid external modifications
    }

    @Override
    public void update(T item) {
        String id = getIdFromObject(item);
        Optional<T> existingItem = vector.stream()
                .filter(it -> getIdFromObject(it).equals(id))
                .findFirst();
        existingItem.ifPresent(it -> {
            vector.remove(it);
            vector.add(item);
        });
    }

    @Override
    public void delete(T item) {
        vector.remove(item);
    }
}
