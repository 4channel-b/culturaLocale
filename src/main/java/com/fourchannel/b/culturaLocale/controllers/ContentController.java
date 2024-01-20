package com.fourchannel.b.culturaLocale.controllers;

import com.fourchannel.b.culturaLocale.dataModels.ApprovalStatus;
import com.fourchannel.b.culturaLocale.dataModels.Event;
import com.fourchannel.b.culturaLocale.dataModels.Itinerary;
import com.fourchannel.b.culturaLocale.dataModels.PointOfInterest;
import com.fourchannel.b.culturaLocale.services.ContentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/content")
public class ContentController {
    private final ContentService contentService;

    public ContentController(ContentService contentService) {
        this.contentService = contentService;
    }
    @PostMapping("/add/itinerary")
    public ResponseEntity<?> createItinerary(@RequestBody Itinerary itinerary) {
        Itinerary newItinerary = contentService.createNewItinerary(itinerary);
        return ResponseEntity.ok(newItinerary);
    }
    @PostMapping("/add/event")
    public ResponseEntity<?> createEvent(@RequestBody Event event) {
        try {
            Event newEvent = contentService.createNewEvent(event);
            return ResponseEntity.ok(newEvent);
        }
        catch(Exception e) {
            return ResponseEntity.badRequest().body("Error creating event" + e.getMessage());
        }
    }
    @PostMapping("/add/poi")
    public ResponseEntity<?> createPoi(@RequestBody PointOfInterest pointOfInterest) {
        try {
            PointOfInterest newPoi = contentService.createNewPointOfInterest(pointOfInterest);
            return ResponseEntity.ok(newPoi);
        }
        catch(Exception e) {
            return ResponseEntity.badRequest().body("Error creating Poi" + e.getMessage());
        }

    }

    @GetMapping("/get/itinerary/{id}")
    public ResponseEntity<Itinerary> getItinerary(@PathVariable int id) {
        return ResponseEntity.ok(contentService.getItinerary(id));
    }

    @GetMapping("/get/event/{id}")
    public ResponseEntity<Event> getEvent(@PathVariable int id) {
        return ResponseEntity.ok(contentService.getEvent(id));
    }

    @GetMapping("/get/poi/{id}")
    public ResponseEntity<PointOfInterest> getPoi(@PathVariable int id) {
        return ResponseEntity.ok(contentService.getPoi(id));
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

    @PutMapping("/approve/event")
    public ResponseEntity<Event> approveEvent(@RequestBody Event event)
    {
        event.setStatus(ApprovalStatus.ACCEPTED);
        return ResponseEntity.ok(event);
    }
    @PutMapping("/approve/poi")
    public ResponseEntity<PointOfInterest> approvePoi(@RequestBody PointOfInterest pointOfInterest)
    {
        pointOfInterest.setStatus(ApprovalStatus.ACCEPTED);
        return ResponseEntity.ok(pointOfInterest);
    }
    @PutMapping("/approve/itinerary")
    public ResponseEntity<Itinerary> approveItinerary(@RequestBody Itinerary itinerary)
    {
        itinerary.setStatus(ApprovalStatus.ACCEPTED);
        return ResponseEntity.ok(itinerary);
    }
    //TODO add search by parameters
}
