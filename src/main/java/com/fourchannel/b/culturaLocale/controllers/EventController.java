package com.fourchannel.b.culturaLocale.controllers;

import com.fourchannel.b.culturaLocale.dataModels.DTOs.EventCreationRequestDTO;
import com.fourchannel.b.culturaLocale.dataModels.Event;
import com.fourchannel.b.culturaLocale.services.ContentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/event")
public class EventController implements BaseCrudController<EventCreationRequestDTO, Long>
{
    private final ContentService contentService;

    public EventController(ContentService contentService) {
        this.contentService = contentService;
    }
    @Override
    public ResponseEntity<Event> create(EventCreationRequestDTO dto) {

        Event newEvent = contentService.createNewEvent(new Event(dto));
        return ResponseEntity.ok(newEvent);

    }

    @Override
    public ResponseEntity<Event> getById(Long id) {
        return ResponseEntity.ok(contentService.getEvent(id));
    }

    @Override
    public ResponseEntity<List<Event>> getAll() {
        return ResponseEntity.ok(contentService.getAllEvent());
    }

    @Override
    public ResponseEntity<?> update(EventCreationRequestDTO dto, Long id){

        //Save up event id
        Event elem = new Event(dto);
        elem.setId(id);

        contentService.updateEvent(elem);
        return ResponseEntity.ok("{}");
    }

    @Override
    public ResponseEntity<?> delete(Long aLong) {
        return null;
    }
}
