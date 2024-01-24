package com.fourchannel.b.culturaLocale.repositories;

import com.fourchannel.b.culturaLocale.dataModels.Location;
import com.fourchannel.b.culturaLocale.dataModels.PointOfInterest;
import com.fourchannel.b.culturaLocale.dataModels.PointOfInterestCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PointOfInterestRepository extends CrudRepository<PointOfInterest,Long> {
    List<PointOfInterest> findByNameAndDescriptionAndCategoryAndLocation(
            String name, String description, PointOfInterestCategory category, Location location);
}
