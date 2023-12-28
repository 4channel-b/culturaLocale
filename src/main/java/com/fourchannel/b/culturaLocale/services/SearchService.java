package com.fourchannel.b.culturaLocale.services;

import com.fourchannel.b.culturaLocale.dataModels.*;
import com.fourchannel.b.culturaLocale.repositories.IVectorRepository;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class SearchService {

    // Method to search Events by date range
    public List<Event> searchEventsByDateRange(IVectorRepository<Event> eventRepository, Date startDate, Date endDate) {
        return eventRepository.findAll().stream()
                .filter(event -> !event.getStartDate().before(startDate) && !event.getEndDate().after(endDate))
                .collect(Collectors.toList());
    }

    // Method to search Itineraries by difficulty level
    public List<Itinerary> searchItinerariesByDifficulty(IVectorRepository<Itinerary> itineraryRepository, int difficultyLevel) {
        return itineraryRepository.findAll().stream()
                .filter(itinerary -> itinerary.getDifficultyLevel() == difficultyLevel)
                .collect(Collectors.toList());
    }

    // Method to search Points of Interest by category
    public List<PointOfInterest> searchPointsOfInterestByCategory(IVectorRepository<PointOfInterest> poiRepository, PointOfInterestCategory category) {
        return poiRepository.findAll().stream()
                .filter(poi -> poi.getCategory() == category)
                .collect(Collectors.toList());
    }

    // Method to search Contests by type
    public List<Contest> searchContestsByType(IVectorRepository<Contest> contestRepository, String type) {
        return contestRepository.findAll().stream()
                .filter(contest -> contest.getType().equalsIgnoreCase(type))
                .collect(Collectors.toList());
    }

    // Additional search methods can be added based on different criteria
}