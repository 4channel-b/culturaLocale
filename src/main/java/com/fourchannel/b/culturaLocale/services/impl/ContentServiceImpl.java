package com.fourchannel.b.culturaLocale.services.impl;

import com.fourchannel.b.culturaLocale.dataModels.Event;
import com.fourchannel.b.culturaLocale.dataModels.Itinerary;
import com.fourchannel.b.culturaLocale.dataModels.PointOfInterest;
import com.fourchannel.b.culturaLocale.repositories.EventRepository;
import com.fourchannel.b.culturaLocale.repositories.ItineraryRepository;
import com.fourchannel.b.culturaLocale.repositories.PointOfInterestRepository;
import com.fourchannel.b.culturaLocale.services.ContentService;
import org.springframework.stereotype.Service;

@Service
public class ContentServiceImpl implements ContentService {
    private final ItineraryRepository itinerarioRepository;
    private final PointOfInterestRepository pointOfInterestRepository;
    private final EventRepository eventRepository;
    public ContentServiceImpl(ItineraryRepository itinerarioRepository, PointOfInterestRepository puntoDiInteresseRepository, EventRepository eventoRepository) {

        this.itinerarioRepository = itinerarioRepository;
        this.pointOfInterestRepository = puntoDiInteresseRepository;
        this.eventRepository = eventoRepository;
    }
    public Itinerary createNewItinerary(Itinerary itinerario) {
        //TODO gestire creazione di contenuti non in pending da parte di utenti che non lo possono fare
        return itinerarioRepository.save(itinerario);
    }

    public PointOfInterest createNewPointOfInterest(PointOfInterest pointOfInterest) {
        //TODO gestire creazione di contenuti non in pending da parte di utenti che non lo possono fare
        return pointOfInterestRepository.save(pointOfInterest);
    }

    public Event createNewEvent(Event event) {
        //TODO gestire creazione di contenuti non in pending da parte di utenti che non lo possono fare
        return eventRepository.save(event);
    }
}
