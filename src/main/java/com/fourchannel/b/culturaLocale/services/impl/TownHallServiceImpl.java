package com.fourchannel.b.culturaLocale.services.impl;

import com.fourchannel.b.culturaLocale.dataModels.TownHall;
import com.fourchannel.b.culturaLocale.repositories.TownHallRepository;
import com.fourchannel.b.culturaLocale.services.ContentService;
import com.fourchannel.b.culturaLocale.services.TownHallService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TownHallServiceImpl implements TownHallService{
    private final TownHallRepository townHallRepository;
    private final ContentService contentService;

    public TownHallServiceImpl(TownHallRepository townHallRepository, ContentService contentService) {
        if (townHallRepository == null) {
            throw new IllegalArgumentException("| ERROR | TownHallRepository is NULL");
        }

        this.townHallRepository = townHallRepository;
        this.contentService = contentService;
    }

    /**
     * Creates and saves a new town hall. Validates that the town hall object is not null.
     *
     * @param townHall The town hall to be created.
     * @return The saved town hall entity.
     * @throws IllegalArgumentException if the town hall object is null.
     */
    @Override
    public TownHall createTownHall(TownHall townHall) {
        if (townHall == null) {
            throw new IllegalArgumentException("| ERROR | TownHall is NULL");
        }

        return townHallRepository.save(townHall);
    }

    /**
     * Retrieves a town hall by its ID.
     * Throws IllegalArgumentException if the town hall is not found.
     *
     * @param aLong The ID of the town hall to retrieve.
     * @return The retrieved town hall.
     * @throws IllegalArgumentException if the town hall is not found.
     */
    @Override
    public TownHall getById(Long aLong) {
        return townHallRepository.findById(aLong)
                .orElseThrow(() -> new IllegalArgumentException("| ERROR | TownHall not found"));
    }

    /**
     * Retrieves all town halls available in the system.
     *
     * @return A list of all town halls.
     */
    @Override
    public List<TownHall> getAll() {
        return StreamSupport.stream(townHallRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    /**
     * Updates a town hall with new information.
     * Validates the existence of the town hall by its ID before updating.
     *
     * @param townHall The new town hall information to update.
     * @param aLong The ID of the town hall to update.
     * @return The updated town hall entity.
     * @throws IllegalArgumentException if the town hall is not found.
     */
    @Override
    public TownHall update(TownHall townHall, Long aLong) {
        townHallRepository.findById(aLong)
                .orElseThrow(() -> new IllegalArgumentException("| ERROR | TownHall not found"));
        townHall.setId(aLong);
        townHallRepository.save(townHall);

        return townHall;
    }

    /**
     * Deletes a town hall by its ID.
     * Validates the existence of the town hall before deleting and also
     * deletes all references to this town hall in other entities.
     *
     * @param aLong The ID of the town hall to delete.
     * @throws IllegalArgumentException if the town hall is not found.
     */
    @Override
    public void delete(Long aLong) {
        townHallRepository.findById(aLong)
                 .orElseThrow(() -> new IllegalArgumentException("| ERROR | TownHall not found"));

        contentService.deleteTownHallReferences(aLong);
        townHallRepository.deleteById(aLong);
    }
}
