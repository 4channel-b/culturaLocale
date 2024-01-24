package com.fourchannel.b.culturaLocale.services;

import com.fourchannel.b.culturaLocale.dataModels.*;

import java.util.Date;
import java.util.List;

public interface SearchService {
    List<Contest> searchContests(String name, Date startDate, Date endDate, String type);
    List<Content> searchContent(String name, String description, Date creationDate, String contentType);
    List<Itinerary> searchItineraries(String name, String description, Date creationDate, int difficultyLevel, double estimatedDuration);
    List<PointOfInterest> searchPointsOfInterest(String name, String description, PointOfInterestCategory category, Location location);
    List<Event> searchEvents(String name, String description, Date startDate, Date endDate, Location location);
}
