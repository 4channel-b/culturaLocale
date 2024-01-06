package com.fourchannel.b.culturaLocale.controllers;

import com.fourchannel.b.culturaLocale.dataModels.Event;
import com.fourchannel.b.culturaLocale.dataModels.Itinerary;
import com.fourchannel.b.culturaLocale.dataModels.PointOfInterest;
import com.fourchannel.b.culturaLocale.services.ContentService;
import com.fourchannel.b.culturaLocale.services.impl.ContentServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/content")
public class ContentController {
    private final ContentService contentService;

    public ContentController(ContentService contentService) {
        this.contentService = contentService;
    }
    @PostMapping("/add/itinerary")
    public ResponseEntity<Itinerary> createItinerary(@RequestBody Itinerary itinerary) {
        //TODO gestire creazione di contenuti non in pending da parte di utenti che non lo possono fare
        Itinerary newItinerary = contentService.createNewItinerary(itinerary);
        return ResponseEntity.ok(newItinerary);
    }
    @PostMapping("/add/event")
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        //TODO gestire creazione di contenuti non in pending da parte di utenti che non lo possono fare
        Event newEvent = contentService.createNewEvent(event);
        return ResponseEntity.ok(newEvent);
    }
    @PostMapping("/add/poi")
    public ResponseEntity<PointOfInterest> createPoi(@RequestBody PointOfInterest pointOfInterest) {
        //TODO gestire creazione di contenuti non in pending da parte di utenti che non lo possono fare
        PointOfInterest newPoi = contentService.createNewPointOfInterest(pointOfInterest);
        return ResponseEntity.ok(newPoi);
    }
}
