package com.fourchannel.b.culturaLocale.services.impl;

import com.fourchannel.b.culturaLocale.dataModels.*;
import com.fourchannel.b.culturaLocale.dataModels.users.Role;
import com.fourchannel.b.culturaLocale.dataModels.users.TownHallRoleUser;
import com.fourchannel.b.culturaLocale.dataModels.users.User;
import com.fourchannel.b.culturaLocale.repositories.*;
import com.fourchannel.b.culturaLocale.services.ContentService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ContentServiceImpl implements ContentService {
    private final ItineraryRepository itineraryRepository;
    private final PointOfInterestRepository pointOfInterestRepository;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final ContentRepository contentRepository;
    private final TownHallRoleRepository townHallRoleRepository;
    private final TownHallRepository townHallRepository;

    private LocalDateTime actualTime;

    public ContentServiceImpl(ItineraryRepository itineraryRepository,
                              PointOfInterestRepository pointOfInterestRepository,
                              EventRepository eventRepository,
                              UserRepository userRepository, ContentRepository contentRepository,
                              TownHallRoleRepository townHallRoleRepository,
                              TownHallRepository townHallRepository) {

        this.itineraryRepository = itineraryRepository;
        this.pointOfInterestRepository = pointOfInterestRepository;
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
        this.contentRepository = contentRepository;
        this.townHallRoleRepository = townHallRoleRepository;
        this.townHallRepository = townHallRepository;
        this.actualTime = LocalDateTime.now();
    }
    private ApprovalStatus genericApprovalDecision(Role role) {
        return switch (role) {
            case PlatformManager, Animator, Curator, AuthorizedContributor -> ApprovalStatus.ACCEPTED;
            default -> ApprovalStatus.PENDING;
        };
    }
    private Optional<ApprovalStatus> getDefaultApprovalStatusFromUser(Long id, Long townHallId) {
        userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("| ERROR | User doesn't exist"));

        townHallRepository.findById(townHallId).
                orElseThrow(() -> new IllegalArgumentException("| ERROR | TownHall doesn't exist"));

        List<TownHallRoleUser> roles = townHallRoleRepository.findTownHallRolesByUserId(id);

        for (TownHallRoleUser role : roles) {
            if (role.getTownHall().getId().equals(townHallId)) {
                return Optional.of(genericApprovalDecision(role.getRole()));
            }
        }

        return Optional.empty();
    }
    public void deleteTownHallReferences(Long townHallId) {
        List<Long> ids = new ArrayList<>();

        contentRepository.findAll().forEach(c -> {
            if (c.getTownHall().getId().equals(townHallId)) {
                ids.add(c.getId());
            }
        });

        // first clear all contents
        ids.forEach(contentRepository::deleteById);
        ids.clear();

        townHallRoleRepository.findAll().forEach(thr -> {
            if (thr.getTownHall().getId().equals(townHallId)) {
                ids.add(thr.getId());
            }
        });

        // then clear all roles
        ids.forEach(townHallRoleRepository::deleteById);
    }
    public Itinerary createNewItinerary(Itinerary itinerary,List<Long> contents) {
        if (itinerary == null) {
            throw new IllegalArgumentException("| ERROR | Itinerary is NULL");
        }


        itinerary.setCreator(userRepository.findById(itinerary.getCreator().getId())
                .orElseThrow(() -> new IllegalArgumentException("| ERROR | Creator doesn't exist")));

        itinerary.setTownHall(townHallRepository.findById(itinerary.getTownHall().getId())
                .orElseThrow(() -> new IllegalArgumentException("| ERROR | TownHall doesn't exist")));

        for (Long id : contents)
        {
            Content content = contentRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("| ERROR | Content doesn't exist"));

            itinerary.getContents().add(content);
        }

        itinerary.setStatus(getDefaultApprovalStatusFromUser(itinerary.getCreator().getId(), itinerary.getTownHall().getId())
                .orElseThrow(() -> new IllegalArgumentException("| ERROR | User doesn't have an approval status")));

        return itineraryRepository.save(itinerary);
    }

    public PointOfInterest createNewPointOfInterest(PointOfInterest pointOfInterest) {
        if (pointOfInterest == null) {
            throw new IllegalArgumentException("| ERROR | PointOfInterest is NULL");
        }

        pointOfInterest.setCreator(userRepository.findById(pointOfInterest.getCreator().getId())
                .orElseThrow(() -> new IllegalArgumentException("| ERROR | Creator doesn't exist")));

        pointOfInterest.setTownHall(townHallRepository.findById(pointOfInterest.getTownHall().getId())
                .orElseThrow(() -> new IllegalArgumentException("| ERROR | TownHall doesn't exist")));

        pointOfInterest.setStatus(getDefaultApprovalStatusFromUser(pointOfInterest.getCreator().getId(), pointOfInterest.getTownHall().getId())
                .orElseThrow(() -> new IllegalArgumentException("| ERROR | User doesn't have an approval status")));

        return pointOfInterestRepository.save(pointOfInterest);
    }

    public Event createNewEvent(Event event)
    {
        if (event == null) {
            throw new IllegalArgumentException("| ERROR | Event is NULL");
        }

        event.setCreator(userRepository.findById(event.getCreator().getId())
                .orElseThrow(() -> new IllegalArgumentException("| ERROR | Creator doesn't exist")));

        event.setTownHall(townHallRepository.findById(event.getTownHall().getId())
                .orElseThrow(() -> new IllegalArgumentException("| ERROR | TownHall doesn't exist")));
        event.setStatus(getDefaultApprovalStatusFromUser(event.getCreator().getId(), event.getTownHall().getId())
                .orElseThrow(() -> new IllegalArgumentException("| ERROR | User doesn't have an approval status")));

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

    @Override
    public Content getContent(Long id) {
        return contentRepository.findById(id).orElseThrow();
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

        if (actualTime.isAfter(event.getEndDate())) {
            throw new IllegalArgumentException("| ERROR | Event is already Expired");
        }



        //Does not have isExpired() check cause already has endDate in it

        event.setTownHall(townHallRepository.findById(event.getTownHall().getId())
                .orElseThrow(() -> new IllegalArgumentException("| ERROR | TownHall doesn't exist")));
        event.setCreator(userRepository.findById(event.getCreator().getId())
                .orElseThrow(() -> new IllegalArgumentException("| ERROR | Creator doesn't exist")));

        eventRepository.save(event);
    }

    @Override
    public void updatePoi(PointOfInterest pointOfInterest)
    {
        if (pointOfInterest == null) {
            throw new IllegalArgumentException("| ERROR | PointOfInterest is NULL");
        }

        pointOfInterest.setTownHall(townHallRepository.findById(pointOfInterest.getTownHall().getId())
                .orElseThrow(() -> new IllegalArgumentException("| ERROR | TownHall doesn't exist")));
        pointOfInterest.setCreator(userRepository.findById(pointOfInterest.getCreator().getId())
                .orElseThrow(() -> new IllegalArgumentException("| ERROR | Creator doesn't exist")));

        pointOfInterestRepository.save(pointOfInterest);
    }

    @Override
    public void updateItinerary(Itinerary itinerary, List<Long> contents)
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
        for (Long id : contents)
        {
            Content content = contentRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("| ERROR | Content doesn't exist"));
            itinerary.getContents().add(content);
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

    public boolean canUserApproveContent(Long contentId, Long userId) {
        Content content = contentRepository.findById(contentId)
                .orElseThrow(() -> new IllegalArgumentException("| ERROR | Content doesn't exist"));
        Role role = townHallRoleRepository.findTownHallRolesByUserId(userId)
                .stream()
                .filter(townHallRoleUser -> townHallRoleUser.getTownHall().getId().equals(content.getTownHall().getId()))
                .map(TownHallRoleUser::getRole)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("| ERROR | User doesn't have a role in this town hall"));

        return role == Role.Curator;
    }

}
