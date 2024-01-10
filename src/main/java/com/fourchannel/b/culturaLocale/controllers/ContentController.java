package com.fourchannel.b.culturaLocale.controllers;

import com.fourchannel.b.culturaLocale.dataModels.Event;
import com.fourchannel.b.culturaLocale.dataModels.Itinerary;
import com.fourchannel.b.culturaLocale.dataModels.PointOfInterest;
import com.fourchannel.b.culturaLocale.services.ContentService;
import com.fourchannel.b.culturaLocale.services.impl.ContentServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/content")
public class ContentController {
    private final ContentService contentService;

    public ContentController(ContentService contentService) {
        this.contentService = contentService;
    }
    @PostMapping("/add/itinerary")
    public ResponseEntity<Itinerary> createItinerary(@RequestBody Itinerary itinerary) {
        Itinerary newItinerary = contentService.createNewItinerary(itinerary);
        return ResponseEntity.ok(newItinerary);
    }
    @PostMapping("/add/event")
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        Event newEvent = contentService.createNewEvent(event);
        return ResponseEntity.ok(newEvent);
    }
    @PostMapping("/add/poi")
    public ResponseEntity<PointOfInterest> createPoi(@RequestBody PointOfInterest pointOfInterest) {
        PointOfInterest newPoi = contentService.createNewPointOfInterest(pointOfInterest);
        return ResponseEntity.ok(newPoi);
    }
    @GetMapping("/getAll/poi")
    public ResponseEntity<List<PointOfInterest>> getAllPoi(){
        return ResponseEntity.ok(contentService.getAllPoi());
    }
    @GetMapping("/getAll/event")
    public ResponseEntity<List<Event>> getAllEvent(){
        return ResponseEntity.ok(contentService.getAllEvent());
    }
    @GetMapping("/getAll/itinerary")
    public ResponseEntity<List<Itinerary>> getAllItinerary(){
        return ResponseEntity.ok(contentService.getAllItinerary());
    }
    //TODO add search by parameters
}
