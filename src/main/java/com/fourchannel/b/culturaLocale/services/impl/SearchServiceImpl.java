package com.fourchannel.b.culturaLocale.services.impl;

import com.fourchannel.b.culturaLocale.dataModels.*;
import com.fourchannel.b.culturaLocale.dataModels.users.Role;
import com.fourchannel.b.culturaLocale.dataModels.users.TownHallRoleUser;
import com.fourchannel.b.culturaLocale.dataModels.users.User;
import com.fourchannel.b.culturaLocale.repositories.*;
import com.fourchannel.b.culturaLocale.services.SearchService;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchServiceImpl implements SearchService {
    private final ContestRepository contestRepository;
    private final ItineraryRepository itineraryRepository;
    private final PointOfInterestRepository pointOfInterestRepository;
    private final EventRepository eventRepository;
    private final ContentRepository contentRepository;
    private final TownHallRoleRepository townHallRoleRepository;
    private final UserRepository userRepository;

    public SearchServiceImpl(ContestRepository contestRepository,
                             ItineraryRepository itineraryRepository,
                             PointOfInterestRepository pointOfInterestRepository,
                             EventRepository eventRepository,
                             ContentRepository contentRepository, TownHallRoleRepository townHallRoleRepository, UserRepository userRepository) {

        this.contestRepository = contestRepository;
        this.itineraryRepository = itineraryRepository;
        this.pointOfInterestRepository = pointOfInterestRepository;
        this.eventRepository = eventRepository;
        this.contentRepository = contentRepository;
        this.townHallRoleRepository = townHallRoleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Contest> searchContests(String name, Date startDate, Date endDate, String type) {
        // Assuming a method in ContestRepository: findBy...
        return contestRepository.findByNameAndInitialDateBetweenAndType(name, startDate, endDate, type);
    }

    @Override
    public List<Content> searchContent(String name, String description, Date creationDate, String contentType) {
        List<Content> results = contentRepository.findByNameAndDescriptionAndCreationDate(name, description, creationDate);
        if (contentType != null && !contentType.isEmpty()) {
            return results.stream()
                    .filter(content -> {
                        try {
                            return contentType.equals(content.getContentType());
                        } catch (ExecutionControl.NotImplementedException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .collect(Collectors.toList());
        }
        return results;
    }

    @Override
    public List<Itinerary> searchItineraries(String name, String description, Date creationDate, int difficultyLevel) {
        return itineraryRepository.findByNameAndDescriptionAndCreationDateAndDifficultyLevel(
                name, description, creationDate, difficultyLevel);
    }

    @Override
    public List<PointOfInterest> searchPointsOfInterest(String name, String description, PointOfInterestCategory category, Location location) {
        return pointOfInterestRepository.findByNameAndDescriptionAndCategoryAndLocation(
                name, description, category, location);
    }

    @Override
    public List<Event> searchEvents(String name, String description, Date startDate, Date endDate) {
        return eventRepository.findEventsByNameAndDescriptionWithinDateRange(
                name, description, startDate, endDate);
    }

    @Override
    public List<User> searchUsersByRole(int role) {
        return townHallRoleRepository.findUserByRole(Role.values()[role]);
    }

    @Override
    public List<User> searchUsersByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}