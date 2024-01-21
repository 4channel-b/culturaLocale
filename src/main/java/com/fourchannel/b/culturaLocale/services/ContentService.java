package com.fourchannel.b.culturaLocale.services;

import com.fourchannel.b.culturaLocale.dataModels.Event;
import com.fourchannel.b.culturaLocale.dataModels.Itinerary;
import com.fourchannel.b.culturaLocale.dataModels.PointOfInterest;

import java.util.List;

public interface ContentService {
    Itinerary createNewItinerary(Itinerary itinerario);

    PointOfInterest createNewPointOfInterest(PointOfInterest pointOfInterest);
    
    Event createNewEvent(Event event);
    
    Itinerary getItinerary(int id);

    PointOfInterest getPoi(int id);

    Event getEvent(int id);

    List<Event> getAllEvent();

    List<Itinerary> getAllItinerary();

    List<PointOfInterest> getAllPoi();

    void updateEvent(Event event);
    void updatePoi(PointOfInterest pointOfInterest);
    void updateItinerary(Itinerary itinerary);
}
