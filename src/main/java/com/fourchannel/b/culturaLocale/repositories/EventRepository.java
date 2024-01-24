package com.fourchannel.b.culturaLocale.repositories;

import com.fourchannel.b.culturaLocale.dataModels.Event;
import com.fourchannel.b.culturaLocale.dataModels.Location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface EventRepository extends CrudRepository<Event,Long> {
    List<Event> findByNameAndDescriptionAndStartDateBetweenAndLocation(
            String name, String description, Date startDate, Date endDate, Location location);
}