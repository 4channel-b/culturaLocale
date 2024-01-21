package com.fourchannel.b.culturaLocale.controllers;

import com.fourchannel.b.culturaLocale.dataModels.Event;
import com.fourchannel.b.culturaLocale.dataModels.Itinerary;
import com.fourchannel.b.culturaLocale.services.ContentService;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class EventController implements BaseCrudController<Event, Long>
{
    private final ContentService contentService;

    public EventController(ContentService contentService) {
        this.contentService = contentService;
    }
    @Override
    public ResponseEntity<Event> create(Event entity) {
        Event newEvent = contentService.createNewEvent(entity);
        return ResponseEntity.ok(newEvent);
    }

    @Override
    public ResponseEntity<Event> getById(Long id) {
        return ResponseEntity.ok(contentService.getEvent(Math.toIntExact(id))); //Da rivedere
    }

    @Override
    public ResponseEntity<List<Event>> getAll() {
        return ResponseEntity.ok(contentService.getAllEvent());
    }

    @Override
    public ResponseEntity<?> update(Event entity) {
        contentService.updateEvent(entity);
        return ResponseEntity.ok("{}");
    }

    @Override
    public ResponseEntity<?> delete(Long aLong) {
        return null;
    }
}
