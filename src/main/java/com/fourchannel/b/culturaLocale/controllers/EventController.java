package com.fourchannel.b.culturaLocale.controllers;

import com.fourchannel.b.culturaLocale.dataModels.DTOs.EventCreationRequestDTO;
import com.fourchannel.b.culturaLocale.dataModels.DTOs.Mappers.EventMapper;
import com.fourchannel.b.culturaLocale.dataModels.Event;
import com.fourchannel.b.culturaLocale.dataModels.Itinerary;
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
    public ResponseEntity<Event> create(EventCreationRequestDTO eventCreationRequestDTO) {

        Event newEvent = contentService.createNewEvent(EventMapper.INSTANCE.eventDtoToEvent(eventCreationRequestDTO),
                                                       eventCreationRequestDTO.getCreator());
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
    public ResponseEntity<?> update(EventCreationRequestDTO entity){
        //contentService.updateEvent(entity);
        return ResponseEntity.ok("{}");
    }

    @Override
    public ResponseEntity<?> delete(Long aLong) {
        return null;
    }
}
