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
    public Itinerary createNewItinerary(Itinerary itinerario)
    {
        if(itinerario == null)
        {
            throw new IllegalArgumentException("| ERROR | Itinerary is NULL");
        }
        //TODO gestire creazione di contenuti non in pending da parte di utenti che non lo possono fare
        return itineraryRepository.save(itinerario);
    }

    public PointOfInterest createNewPointOfInterest(PointOfInterest pointOfInterest)
    {
        if(pointOfInterest == null)
        {
            throw new IllegalArgumentException("| ERROR | pointOfInterest is NULL");
        }
        //TODO gestire creazione di contenuti non in pending da parte di utenti che non lo possono fare
        return pointOfInterestRepository.save(pointOfInterest);
    }

    public Event createNewEvent(Event event)
    {
        if(event == null)
        {
            throw new IllegalArgumentException("| ERROR | Event is NULL");
        }
        //TODO gestire creazione di contenuti non in pending da parte di utenti che non lo possono fare
        return eventRepository.save(event);
    }

    @Override
    public Itinerary getItinerary(int id)
    {
        if(id < 0)
        {
            throw  new IllegalArgumentException("| ERROR | Id must not be negative :(");
        }
       return itineraryRepository.findById(String.valueOf(id));
    }
    
    @Override
    public PointOfInterest getPoi(int id)
    {
        if(id < 0)
        {
            throw  new IllegalArgumentException("| ERROR | Id must not be negative :(");
        }
        return pointOfInterestRepository.findById(String.valueOf(id));
    }
    
    @Override
    public Event getEvent(int id)
    {
        if(id < 0)
        {
            throw  new IllegalArgumentException("| ERROR | Id must not be negative :(");
        }
        return eventRepository.findById(String.valueOf(id));
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

    @Override
    public void updateEvent(Event event)
    {
        if(event == null)
        {
            throw new IllegalArgumentException("| ERROR | Event is NULL");
        }
        eventRepository.update(event);
    }

    @Override
    public void updatePoi(PointOfInterest pointOfInterest)
    {
        if(pointOfInterest == null)
        {
            throw new IllegalArgumentException("| ERROR | PointOfInterest is NULL");
        }
        pointOfInterestRepository.update(pointOfInterest);
    }

    @Override
    public void updateEvent(Itinerary itinerary)
    {
        if(itinerary == null)
        {
            throw new IllegalArgumentException("| ERROR | Itinerary is NULL");
        }
        itineraryRepository.update(itinerary);
    }
}
