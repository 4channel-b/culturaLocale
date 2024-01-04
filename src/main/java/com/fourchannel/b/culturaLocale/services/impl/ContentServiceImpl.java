package com.fourchannel.b.culturaLocale.services;

import com.fourchannel.b.culturaLocale.repositories.EventRepository;
import com.fourchannel.b.culturaLocale.repositories.ItineraryRepository;
import com.fourchannel.b.culturaLocale.repositories.PointOfInterestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContentService {
    private final ItineraryRepository itinerarioRepository;
    private final PointOfInterestRepository puntoDiInteresseRepository;
    private final EventRepository eventoRepository;
    public ContentService(ItineraryRepository itinerarioRepository, PointOfInterestRepository puntoDiInteresseRepository, EventRepository eventoRepository) {
        this.itinerarioRepository = itinerarioRepository;
        this.puntoDiInteresseRepository = puntoDiInteresseRepository;
        this.eventoRepository = eventoRepository;
    }
}
