package com.fourchannel.b.culturaLocale.controllers;

import com.fourchannel.b.culturaLocale.dataModels.Itinerary;
import com.fourchannel.b.culturaLocale.services.ContentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/itinerary")
public class ItineraryController implements BaseCrudController<Itinerary, Long>
{
    private final ContentService contentService;

    public ItineraryController(ContentService contentService) {
        this.contentService = contentService;
    }
    @Override
    public ResponseEntity<Itinerary> create(Itinerary entity) {
        Itinerary newItinerary = contentService.createNewItinerary(entity);
        return ResponseEntity.ok(newItinerary);
    }

    @Override
    public ResponseEntity<Itinerary> getById(Long id) {
        return ResponseEntity.ok(contentService.getItinerary(Math.toIntExact(id))); //Da rivedere
    }

    @Override
    public ResponseEntity<List<Itinerary>> getAll() {
        return ResponseEntity.ok(contentService.getAllItinerary());
    }

    @Override
    public ResponseEntity<?> update(Itinerary entity) {
        contentService.updateItinerary(entity);
        return ResponseEntity.ok("{}");
    }

    @Override
    public ResponseEntity<?> delete(Long aLong) {
        return null;
    }
}
