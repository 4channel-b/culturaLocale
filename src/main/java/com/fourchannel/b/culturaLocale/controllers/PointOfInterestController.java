package com.fourchannel.b.culturaLocale.controllers;

import com.fourchannel.b.culturaLocale.dataModels.DTOs.PointOfInterestCreationRequestDTO;
import com.fourchannel.b.culturaLocale.dataModels.PointOfInterest;
import com.fourchannel.b.culturaLocale.services.ContentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/poi")
public class PointOfInterestController implements BaseCrudController<PointOfInterestCreationRequestDTO, Long>
{
    private final ContentService contentService;

    public PointOfInterestController(ContentService contentService) {
        this.contentService = contentService;
    }
    @Override
    public ResponseEntity<PointOfInterest> create(PointOfInterestCreationRequestDTO dto) {
        PointOfInterest newPointOfInterest = contentService.createNewPointOfInterest(new PointOfInterest(dto), dto.getCreator());
        return ResponseEntity.ok(newPointOfInterest);
    }

    @Override
    public ResponseEntity<PointOfInterest> getById(Long id) {
        return ResponseEntity.ok(contentService.getPoi(id));
    }

    @Override
    public ResponseEntity<List<PointOfInterest>> getAll() {
        return ResponseEntity.ok(contentService.getAllPoi());
    }

    @Override
    public ResponseEntity<?> update(PointOfInterestCreationRequestDTO dto, Long id) {
        PointOfInterest elem = new PointOfInterest(dto);
        elem.setId(id);

        contentService.updatePoi(elem);
        return ResponseEntity.ok("{}");
    }
    @Override
    public ResponseEntity<?> delete(Long id) {
        return null;
    }
}
