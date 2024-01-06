package com.fourchannel.b.culturaLocale.services;

import com.fourchannel.b.culturaLocale.dataModels.Event;
import com.fourchannel.b.culturaLocale.dataModels.Itinerary;
import com.fourchannel.b.culturaLocale.dataModels.PointOfInterest;

public interface ContentService {
    public Itinerary createNewItinerary(Itinerary itinerario);
    public PointOfInterest createNewPointOfInterest(PointOfInterest pointOfInterest);
    public Event createNewEvent(Event event);
}
