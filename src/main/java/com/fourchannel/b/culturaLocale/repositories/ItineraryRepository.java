package com.fourchannel.b.culturaLocale.repositories;

import com.fourchannel.b.culturaLocale.dataModels.Itinerary;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ItineraryRepository extends CrudRepository<Itinerary,Long> {
    List<Itinerary> findByNameAndDescriptionAndCreationDateAndDifficultyLevel(
            String name, String description, Date creationDate, int difficultyLevel);
}
