package com.fourchannel.b.culturaLocale.controllers;

import com.fourchannel.b.culturaLocale.dataModels.*;
import com.fourchannel.b.culturaLocale.dataModels.users.User;
import com.fourchannel.b.culturaLocale.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {

    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/contests")
    public List<Contest> searchContests(@RequestParam String name,
                                        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
                                        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate,
                                        @RequestParam String type) {
        return searchService.searchContests(name, startDate, endDate, type);
    }

    @GetMapping("/content")
    public List<Content> searchContent(@RequestParam String name,
                                       @RequestParam String description,
                                       @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date creationDate,
                                       @RequestParam(required = false) String contentType) {
        return searchService.searchContent(name, description, creationDate, contentType);
    }

    @GetMapping("/itineraries")
    public List<Itinerary> searchItineraries(@RequestParam String name,
                                             @RequestParam String description,
                                             @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date creationDate,
                                             @RequestParam int difficultyLevel,
                                             @RequestParam double estimatedDuration) {
        return searchService.searchItineraries(name, description, creationDate, difficultyLevel, estimatedDuration);
    }

    @GetMapping("/pois")
    public List<PointOfInterest> searchPointsOfInterest(@RequestParam String name,
                                                        @RequestParam String description,
                                                        @RequestParam PointOfInterestCategory category,
                                                        @RequestParam Location location) {
        return searchService.searchPointsOfInterest(name, description, category, location);
    }

    @GetMapping("/events")
    public List<Event> searchEvents(@RequestParam String name,
                                    @RequestParam String description,
                                    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
                                    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {
        return searchService.searchEvents(name, description, startDate, endDate);
    }
    @GetMapping("/usersByRole")
    public List<User> searchUser(@RequestParam int role){
        return searchService.searchUsersByRole(role);
    }
    @GetMapping("/usersByMail")
    public List<User> searchUser(@RequestParam String email){
        return searchService.searchUsersByEmail(email);
    }
}
