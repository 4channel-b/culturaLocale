package com.fourchannel.b.culturaLocale.services.impl;

import com.fourchannel.b.culturaLocale.dataModels.*;
import com.fourchannel.b.culturaLocale.repositories.*;
import com.fourchannel.b.culturaLocale.services.ContentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ContentServiceImpl implements ContentService {
    private final ItineraryRepository itineraryRepository;
    private final PointOfInterestRepository pointOfInterestRepository;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    public ContentServiceImpl(ItineraryRepository itineraryRepository,
                              PointOfInterestRepository pointOfInterestRepository,
                              EventRepository eventRepository,
                              UserRepository userRepository) {

        this.itineraryRepository = itineraryRepository;
        this.pointOfInterestRepository = pointOfInterestRepository;
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }
    public Itinerary createNewItinerary(Itinerary itinerary)
    {
        if(itinerary == null)
        {
            throw new IllegalArgumentException("| ERROR | Itinerary is NULL");
        }

        //TODO gestire creazione di contenuti non in pending da parte di utenti che non lo possono fare
        //creatorExist(itinerary);
        return itineraryRepository.save(itinerary);

    }

    public PointOfInterest createNewPointOfInterest(PointOfInterest pointOfInterest)
    {
        if(pointOfInterest == null)
        {
            throw new IllegalArgumentException("| ERROR | pointOfInterest is NULL");
        }
        //TODO gestire creazione di contenuti non in pending da parte di utenti che non lo possono fare
        creatorExist(pointOfInterest);
        return pointOfInterestRepository.save(pointOfInterest);
    }

    public Event createNewEvent(Event event)
    {
        if(event == null)
        {
            throw new IllegalArgumentException("| ERROR | Event is NULL");
        }
        //TODO gestire creazione di contenuti non in pending da parte di utenti che non lo possono fare
        creatorExist(event);
        return eventRepository.save(event);
    }

    @Override
    public Itinerary getItinerary(Long id)
    {
        if(id < 0)
        {
            throw  new IllegalArgumentException("| ERROR | Id must not be negative :(");
        }
       return itineraryRepository.findById((long)id).orElse(null);
    }
    
    @Override
    public PointOfInterest getPoi(Long id)
    {
        if(id < 0)
        {
            throw  new IllegalArgumentException("| ERROR | Id must not be negative :(");
        }
        return pointOfInterestRepository.findById(id).orElse(null);
    }
    
    @Override
    public Event getEvent(Long id)
    {
        if(id < 0)
        {
            throw  new IllegalArgumentException("| ERROR | Id must not be negative :(");
        }
        return eventRepository.findById(id).orElse(null);
    }

    public List<Event> getAllEvent() {
        return StreamSupport.stream(eventRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public List<Itinerary> getAllItinerary() {
        return StreamSupport.stream(itineraryRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public List<PointOfInterest> getAllPoi() {
        return StreamSupport.stream(pointOfInterestRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public void updateEvent(Event event)
    {
        if(event == null)
        {
            throw new IllegalArgumentException("| ERROR | Event is NULL");
        }
        eventRepository.save(event);
    }

    @Override
    public void updatePoi(PointOfInterest pointOfInterest)
    {
        if(pointOfInterest == null)
        {
            throw new IllegalArgumentException("| ERROR | PointOfInterest is NULL");
        }
        pointOfInterestRepository.save(pointOfInterest);
    }

    @Override
    public void updateItinerary(Itinerary itinerary)
    {
        if(itinerary == null)
        {
            throw new IllegalArgumentException("| ERROR | Itinerary is NULL");
        }
        itineraryRepository.save(itinerary);
    }

    /**
     * @param id
     */
    @Override
    public void approveEvent(Long id) {
        eventRepository.findById(id).ifPresent(e -> {
            e.setStatus(ApprovalStatus.ACCEPTED);
            eventRepository.save(e);
        });
    }

    /**
     * @param id
     */
    @Override
    public void approvePointOfInterest(Long id) {
        pointOfInterestRepository.findById(id).ifPresent(poi -> {
            poi.setStatus(ApprovalStatus.ACCEPTED);
            pointOfInterestRepository.save(poi);
        });
    }

    /**
     * @param id
     */
    @Override
    public void approveItinerary(Long id) {
        itineraryRepository.findById(id).ifPresent(it -> {
            it.setStatus(ApprovalStatus.ACCEPTED);
            itineraryRepository.save(it);
        });
    }

    private void creatorExist(Content content){
        //if(userRepository.findById(content.getCreator()).isEmpty())
          //  throw new NullPointerException("creator doesn't exist");
    }
}
