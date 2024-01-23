package com.fourchannel.b.culturaLocale.controllers;

import com.fourchannel.b.culturaLocale.dataModels.ApprovalStatus;
import com.fourchannel.b.culturaLocale.dataModels.DTOs.Mappers.UserMapper;
import com.fourchannel.b.culturaLocale.dataModels.Event;
import com.fourchannel.b.culturaLocale.dataModels.Itinerary;
import com.fourchannel.b.culturaLocale.dataModels.PointOfInterest;
import com.fourchannel.b.culturaLocale.services.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PutMapping("/approve/event/{id}")
    public ResponseEntity<?> approveEvent(@PathVariable Long id)
    {
        contentService.approveEvent(id);
        return ResponseEntity.ok().body("{}");
    }
    @PutMapping("/approve/poi/{id}")
    public ResponseEntity<?> approvePoi(@PathVariable Long id)
    {
        contentService.approvePointOfInterest(id);
        return ResponseEntity.ok().body("{}");
    }
    @PutMapping("/approve/itinerary/{id}")
    public ResponseEntity<?> approveItinerary(@PathVariable Long id)
    {
        contentService.approveItinerary(id);
        return ResponseEntity.ok().body("{}");
    }
}
