package com.fourchannel.b.culturaLocale.services.impl;

import com.fourchannel.b.culturaLocale.dataModels.*;
import com.fourchannel.b.culturaLocale.repositories.EventRepository;
import com.fourchannel.b.culturaLocale.repositories.IVectorRepository;
import com.fourchannel.b.culturaLocale.repositories.ItineraryRepository;
import com.fourchannel.b.culturaLocale.repositories.PointOfInterestRepository;
import com.fourchannel.b.culturaLocale.services.ContentService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContentServiceImpl implements ContentService {
    private final ItineraryRepository itineraryRepository;
    private final PointOfInterestRepository pointOfInterestRepository;
    private final EventRepository eventRepository;
    public ContentServiceImpl(ItineraryRepository itinerarioRepository, PointOfInterestRepository puntoDiInteresseRepository, EventRepository eventoRepository) {

        this.itineraryRepository = itinerarioRepository;
        this.pointOfInterestRepository = puntoDiInteresseRepository;
        this.eventRepository = eventoRepository;
    }
    public Itinerary createNewItinerary(Itinerary itinerario) {
        //TODO gestire creazione di contenuti non in pending da parte di utenti che non lo possono fare
        return itineraryRepository.save(itinerario);
    }

    public PointOfInterest createNewPointOfInterest(PointOfInterest pointOfInterest) {
        //TODO gestire creazione di contenuti non in pending da parte di utenti che non lo possono fare
        return pointOfInterestRepository.save(pointOfInterest);
    }

    public Event createNewEvent(Event event) {
        //TODO gestire creazione di contenuti non in pending da parte di utenti che non lo possono fare
        return eventRepository.save(event);
    }

    public List<Event> getAllEvent() {
        return eventRepository.findAll();
    }


    public List<Itinerary> getAllItinerary() {
        return itineraryRepository.findAll();
    }

    public List<PointOfInterest> getAllPoi() {
        return pointOfInterestRepository.findAll();
    }
    // Method to search Events by date range
    public List<Event> searchEventsByDateRange(Date startDate, Date endDate) {
        return eventRepository.findAll().stream()
                .filter(event -> !event.getStartDate().before(startDate) && !event.getEndDate().after(endDate))
                .collect(Collectors.toList());
    }

    // Method to search Itineraries by difficulty level
    public List<Itinerary> searchItinerariesByDifficulty(int difficultyLevel) {
        return itineraryRepository.findAll().stream()
                .filter(itinerary -> itinerary.getDifficultyLevel() == difficultyLevel)
                .collect(Collectors.toList());
    }

    // Method to search Points of Interest by category
    public List<PointOfInterest> searchPointsOfInterestByCategory(PointOfInterestCategory category) {
        return pointOfInterestRepository.findAll().stream()
                .filter(poi -> poi.getCategory() == category)
                .collect(Collectors.toList());
    }
}
