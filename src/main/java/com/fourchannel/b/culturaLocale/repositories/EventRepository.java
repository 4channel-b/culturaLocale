package com.fourchannel.b.culturaLocale.repositories;

import com.fourchannel.b.culturaLocale.dataModels.Event;
import com.fourchannel.b.culturaLocale.dataModels.Location;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface EventRepository extends CrudRepository<Event,Long> {
    @Query("SELECT e FROM Event e WHERE e.name = :name AND e.description = :description AND " +
            "(e.startDate BETWEEN :startDate AND :endDate OR e.endDate BETWEEN :startDate AND :endDate)")
    List<Event> findEventsByNameAndDescriptionWithinDateRange(
            @Param("name") String name,
            @Param("description") String description,
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate);
}