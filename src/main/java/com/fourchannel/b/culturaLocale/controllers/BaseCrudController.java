package com.fourchannel.b.culturaLocale.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Generic CRUD controller interface for managing singular entities.
 *
 * @param <T> the type of the entity
 * @param <ID> the type of the entity identifier
 */
public interface BaseCrudController<T, ID> {

    /**
     * Creates a new entity.
     *
     * @param entity the entity to create
     * @return the created entity wrapped in ResponseEntity
     */
    @PostMapping
    ResponseEntity<T> create(@RequestBody T entity);

    /**
     * Retrieves an entity by its identifier.
     *
     * @param id the identifier of the entity
     * @return the retrieved entity wrapped in ResponseEntity
     */
    @GetMapping("/{id}")
    ResponseEntity<T> getById(@PathVariable ID id);

    /**
     * Retrieves all entities.
     *
     * @return list of all entities wrapped in ResponseEntity
     */
    @GetMapping("/getAll")
    ResponseEntity<List<T>> getAll();

    /**
     * Updates an existing entity.
     *
     * @param entity the entity to update
     * @return the updated entity wrapped in ResponseEntity
     */
    @PutMapping
    ResponseEntity<?> update(@RequestBody T entity);

    /**
     * Deletes an entity by its identifier.
     *
     * @param id the identifier of the entity to delete
     * @return a response entity representing the outcome of the delete operation
     */
    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(@PathVariable ID id);
}