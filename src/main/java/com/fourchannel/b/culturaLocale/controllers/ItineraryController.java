package com.fourchannel.b.culturaLocale.controllers;

import com.fourchannel.b.culturaLocale.dataModels.DTOs.ItineraryCreationRequestDTO;
import com.fourchannel.b.culturaLocale.dataModels.Itinerary;
import com.fourchannel.b.culturaLocale.services.ContentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/itinerary")
public class ItineraryController implements BaseCrudController<ItineraryCreationRequestDTO, Long>
{
    private final ContentService contentService;

    public ItineraryController(ContentService contentService) {
        this.contentService = contentService;
    }
    @Override
    public ResponseEntity<Itinerary> create(ItineraryCreationRequestDTO dto) {
        Itinerary newItinerary = contentService.createNewItinerary(new Itinerary(dto), dto.getContents());
        return ResponseEntity.ok(newItinerary);
    }

    @Override
    public ResponseEntity<Itinerary> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(contentService.getItinerary(id));
    }

    @Override
    public ResponseEntity<List<Itinerary>> getAll() {
        return ResponseEntity.ok(contentService.getAllItinerary());
    }

    @Override
    public ResponseEntity<?> update(ItineraryCreationRequestDTO dto, Long id) {
        Itinerary it = new Itinerary(dto);
        it.setId(id);

        contentService.updateItinerary(it, dto.getContents());
        return ResponseEntity.ok("{}");
    }

    @Override
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return null;
    }
}
