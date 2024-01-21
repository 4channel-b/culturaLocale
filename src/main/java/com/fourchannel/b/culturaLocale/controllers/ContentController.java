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
}
