package com.fourchannel.b.culturaLocale.services;

import com.fourchannel.b.culturaLocale.dataModels.Content;
import com.fourchannel.b.culturaLocale.dataModels.Event;
import com.fourchannel.b.culturaLocale.dataModels.Itinerary;
import com.fourchannel.b.culturaLocale.dataModels.PointOfInterest;
import com.fourchannel.b.culturaLocale.dataModels.users.User;

import java.util.List;

public interface ContentService {
    Itinerary createNewItinerary(Itinerary itinerary, Long user, List<Long> contents);

    PointOfInterest createNewPointOfInterest(PointOfInterest pointOfInterest, Long user);
    
    Event createNewEvent(Event event, Long user);
    
    Itinerary getItinerary(Long id);

    PointOfInterest getPoi(Long id);

    Event getEvent(Long id);
    Content getContent(Long id);

    List<Event> getAllEvent();

    List<Itinerary> getAllItinerary();

    List<PointOfInterest> getAllPoi();

    void updateEvent(Event event);
    void updatePoi(PointOfInterest pointOfInterest);
    void updateItinerary(Itinerary itinerary, List<Long> contents);

    void approveEvent(Long id);
    void approvePointOfInterest(Long id);
    void approveItinerary(Long id);
    boolean canUserApproveContent(Long contentId, Long userId);
}
