package com.fourchannel.b.culturaLocale.controllers;

import com.fourchannel.b.culturaLocale.dataModels.Itinerary;
import com.fourchannel.b.culturaLocale.dataModels.PointOfInterest;
import com.fourchannel.b.culturaLocale.services.ContentService;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class PointOfInterestController implements BaseCrudController<PointOfInterest, Long>
{
    private final ContentService contentService;

    public PointOfInterestController(ContentService contentService) {
        this.contentService = contentService;
    }
    @Override
    public ResponseEntity<PointOfInterest> create(PointOfInterest entity) {
        PointOfInterest newPointOfInterest = contentService.createNewPointOfInterest(entity);
        return ResponseEntity.ok(newPointOfInterest);
    }

    @Override
    public ResponseEntity<PointOfInterest> getById(Long id) {
        return ResponseEntity.ok(contentService.getPoi(Math.toIntExact(id))); //Da rivedere
    }

    @Override
    public ResponseEntity<List<PointOfInterest>> getAll() {
        return ResponseEntity.ok(contentService.getAllPoi());
    }

    @Override
    public ResponseEntity<?> update(PointOfInterest entity) {
        contentService.updatePoi(entity);
        return ResponseEntity.ok("{}");
    }

    @Override
    public ResponseEntity<?> delete(Long aLong) {
        return null;
    }
}
