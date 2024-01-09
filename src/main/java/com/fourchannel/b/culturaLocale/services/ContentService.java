package com.fourchannel.b.culturaLocale.services;

import com.fourchannel.b.culturaLocale.dataModels.Event;
import com.fourchannel.b.culturaLocale.dataModels.Itinerary;
import com.fourchannel.b.culturaLocale.dataModels.PointOfInterest;
import com.fourchannel.b.culturaLocale.dataModels.PointOfInterestCategory;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public interface ContentService {
    public Itinerary createNewItinerary(Itinerary itinerario);
    public PointOfInterest createNewPointOfInterest(PointOfInterest pointOfInterest);
    public Event createNewEvent(Event event);
    public List<Event> getAllEvent();


    public List<Itinerary> getAllItinerary();

    public List<PointOfInterest> getAllPoi();
    // Method to search Events by date range
    public List<Event> searchEventsByDateRange(Date startDate, Date endDate);

    // Method to search Itineraries by difficulty level
    public List<Itinerary> searchItinerariesByDifficulty(int difficultyLevel);

    // Method to search Points of Interest by category
    public List<PointOfInterest> searchPointsOfInterestByCategory(PointOfInterestCategory category);
}
