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
    private final PointOfInterestRepository puntoDiInteresseRepository;
    private final EventRepository eventoRepository;
    public ContentServiceImpl(ItineraryRepository itinerarioRepository, PointOfInterestRepository puntoDiInteresseRepository, EventRepository eventoRepository) {
        this.itinerarioRepository = itinerarioRepository;
        this.puntoDiInteresseRepository = puntoDiInteresseRepository;
        this.eventoRepository = eventoRepository;
    }
    public Itinerary createNewItinerary(Itinerary itinerario) {
        return itinerarioRepository.save(itinerario);
    }

    public PointOfInterest createNewPointOfInterest(PointOfInterest pointOfInterest) {
        return puntoDiInteresseRepository.save(pointOfInterest);
    }

    public Event createNewEvent(Event event) {
        return eventoRepository.save(event);
    }
}
