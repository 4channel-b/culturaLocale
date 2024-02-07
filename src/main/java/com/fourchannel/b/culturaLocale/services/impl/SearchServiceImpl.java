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

    /**
     * Searches for contests by name, start date, end date, and type. Returns a list of contests matching the criteria.
     *
     * @param name The name of the contest to match.
     * @param startDate The start date of the contest to match.
     * @param endDate The end date of the contest to match.
     * @param type The type of the contest to match.
     * @return A list of contests that match the search criteria.
     */
    @Override
    public List<Contest> searchContests(String name, Date startDate, Date endDate, String type) {
        return contestRepository.findByNameAndInitialDateBetweenAndType(name, startDate, endDate, type);
    }

    /**
     * Searches for content by name, description, creation date, and content type. Filters the results to match the content type if specified.
     *
     * @param name The name of the content to match.
     * @param description The description of the content to match.
     * @param creationDate The creation date of the content to match.
     * @param contentType The type of content to match.
     * @return A list of content that matches the search criteria.
     */
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

    /**
     * Searches for itineraries by name, description, creation date, and difficulty level. Returns a list of itineraries matching the criteria.
     *
     * @param name The name of the itinerary to match.
     * @param description The description of the itinerary to match.
     * @param creationDate The creation date of the itinerary to match.
     * @param difficultyLevel The difficulty level of the itinerary to match.
     * @return A list of itineraries that match the search criteria.
     */
    @Override
    public List<Itinerary> searchItineraries(String name, String description, Date creationDate, int difficultyLevel) {
        return itineraryRepository.findByNameAndDescriptionAndCreationDateAndDifficultyLevel(
                name, description, creationDate, difficultyLevel);
    }

    /**
     * Searches for points of interest by name, description, category, and location. Returns a list of points of interest matching the criteria.
     *
     * @param name The name of the point of interest to match.
     * @param description The description of the point of interest to match.
     * @param category The category of the point of interest to match.
     * @param location The location of the point of interest to match.
     * @return A list of points of interest that match the search criteria.
     */
    @Override
    public List<PointOfInterest> searchPointsOfInterest(String name, String description, PointOfInterestCategory category, Location location) {
        return pointOfInterestRepository.findByNameAndDescriptionAndCategoryAndLocation(
                name, description, category, location);
    }

    /**
     * Searches for events by name, description, start date, and end date. Returns a list of events matching the criteria.
     *
     * @param name The name of the event to match.
     * @param description The description of the event to match.
     * @param startDate The start date of the event to match.
     * @param endDate The end date of the event to match.
     * @return A list of events that match the search criteria.
     */
    @Override
    public List<Event> searchEvents(String name, String description, Date startDate, Date endDate) {
        return eventRepository.findEventsByNameAndDescriptionWithinDateRange(
                name, description, startDate, endDate);
    }

    /**
     * Searches for users by role. Returns a list of users matching the specified role.
     *
     * @param role The role index to match against available roles.
     * @return A list of users with the specified role.
     */
    @Override
    public List<User> searchUsersByRole(int role) {
        return townHallRoleRepository.findUserByRole(Role.values()[role]);
    }

    /**
     * Searches for users by email. Returns a list of users with the specified email.
     *
     * @param email The email of the user to match.
     * @return A list of users with the specified email.
     */
    @Override
    public List<User> searchUsersByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}