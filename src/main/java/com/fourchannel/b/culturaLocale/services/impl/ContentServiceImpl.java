package com.fourchannel.b.culturaLocale.services.impl;

import com.fourchannel.b.culturaLocale.dataModels.*;
import com.fourchannel.b.culturaLocale.dataModels.users.User;
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
    public Itinerary createNewItinerary(Itinerary itinerary, Long creator) {
        if (itinerary == null) {
            throw new IllegalArgumentException("| ERROR | Itinerary is NULL");
        }

        User user = userRepository.findById(creator)
                .orElseThrow(() -> new IllegalArgumentException("| ERROR | Creator doesn't exist"));
        itinerary.setCreator(user);

        return itineraryRepository.save(itinerary);
    }

    public PointOfInterest createNewPointOfInterest(PointOfInterest pointOfInterest, Long creator) {
        if (pointOfInterest == null) {
            throw new IllegalArgumentException("| ERROR | PointOfInterest is NULL");
        }

        User user = userRepository.findById(creator)
                .orElseThrow(() -> new IllegalArgumentException("| ERROR | Creator doesn't exist"));
        pointOfInterest.setCreator(user);

        return pointOfInterestRepository.save(pointOfInterest);
    }

    public Event createNewEvent(Event event,Long creator)
    {
        if (event == null) {
            throw new IllegalArgumentException("| ERROR | Event is NULL");
        }

        User user = userRepository.findById(creator)
                .orElseThrow(() -> new IllegalArgumentException("| ERROR | Creator doesn't exist"));
        event.setCreator(user);

        return eventRepository.save(event);
    }

    @Override
    public Itinerary getItinerary(Long id)
    {
        return itineraryRepository.findById(id).orElseThrow();
    }

    @Override
    public PointOfInterest getPoi(Long id)
    {
        return pointOfInterestRepository.findById(id).orElseThrow();
    }

    @Override
    public Event getEvent(Long id)
    {
        return eventRepository.findById(id).orElseThrow();
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
        if (event == null) {
            throw new IllegalArgumentException("| ERROR | Event is NULL");
        }

        eventRepository.save(event);
    }

    @Override
    public void updatePoi(PointOfInterest pointOfInterest)
    {
        if(pointOfInterest == null) {
            throw new IllegalArgumentException("| ERROR | PointOfInterest is NULL");
        }

        pointOfInterestRepository.save(pointOfInterest);
    }

    private Content fillOutContent(Content content) {
        if (content == null) {
            throw new IllegalArgumentException("| ERROR | Content is NULL");
        }

        if (content instanceof Itinerary) {
            return itineraryRepository.findById(content.getId())
                    .orElseThrow(() -> new IllegalArgumentException("| ERROR | Itinerary doesn't exist"));
        } else if (content instanceof PointOfInterest) {
            return pointOfInterestRepository.findById(content.getId())
                    .orElseThrow(() -> new IllegalArgumentException("| ERROR | PointOfInterest doesn't exist"));
        } else if (content instanceof Event) {
            return eventRepository.findById(content.getId())
                    .orElseThrow(() -> new IllegalArgumentException("| ERROR | Event doesn't exist"));
        } else {
            throw new IllegalArgumentException("| ERROR | Content is not a valid type");
        }
    }

    @Override
    public void updateItinerary(Itinerary itinerary)
    {
        if (itinerary == null) {
            throw new IllegalArgumentException("| ERROR | Itinerary is NULL");
        }

        // find the original, make sure we're not editing something that's not there
        itineraryRepository.findById(itinerary.getId())
                .orElseThrow(() -> new IllegalArgumentException("| ERROR | Itinerary doesn't exist"));

        // build a new itinerary object correctly filled out for the purposes of replacing the old database one

        // fill out its user from the incomplete DTO mapping
        User user = userRepository.findById(itinerary.getCreator().getId())
                .orElseThrow(() -> new IllegalArgumentException("| ERROR | User doesn't exist"));
        itinerary.setCreator(user);

        // fill out its contents from the incomplete DTO mapping
        for (Content itc : itinerary.getContents()) {
            Content filledOut = fillOutContent(itc);
            itinerary.getContents().remove(itc);
            itinerary.getContents().add(filledOut);
        }

        // assumption
        itinerary.setStatus(ApprovalStatus.ACCEPTED);

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
}
