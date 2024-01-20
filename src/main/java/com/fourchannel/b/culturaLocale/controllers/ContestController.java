package com.fourchannel.b.culturaLocale.controllers;

import com.fourchannel.b.culturaLocale.dataModels.Contest;
import com.fourchannel.b.culturaLocale.services.ContestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contest")
public class ContestController {
    private final ContestService contestService;

    public ContestController(ContestService contestService) {
        this.contestService = contestService;
    }
    @PostMapping("/add")
    public ResponseEntity<Contest> createItinerary(@RequestBody Contest contest)
    {
        Contest newContest = contestService.createContest(contest);
        return ResponseEntity.ok(newContest);
    }
}