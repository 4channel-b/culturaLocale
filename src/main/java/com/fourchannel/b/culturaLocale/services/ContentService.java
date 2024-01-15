package com.fourchannel.b.culturaLocale.services;

import com.fourchannel.b.culturaLocale.dataModels.Event;
import com.fourchannel.b.culturaLocale.dataModels.Itinerary;
import com.fourchannel.b.culturaLocale.dataModels.PointOfInterest;
import com.fourchannel.b.culturaLocale.dataModels.PointOfInterestCategory;

import java.util.Date;
import java.util.List;

public interface ContentService {
    public Itinerary createNewItinerary(Itinerary itinerario);

    public PointOfInterest createNewPointOfInterest(PointOfInterest pointOfInterest);
    
    public Event createNewEvent(Event event);
    
    public Itinerary getItinerary(int id);

    public PointOfInterest getPoi(int id);

    public Event getEvent(int id);

    public List<Event> getAllEvent();

    public List<Itinerary> getAllItinerary();

    public List<PointOfInterest> getAllPoi();
}
