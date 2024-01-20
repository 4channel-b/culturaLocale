package com.fourchannel.b.culturaLocale.controllers;

import com.fourchannel.b.culturaLocale.dataModels.TownHall;
import com.fourchannel.b.culturaLocale.services.TownHallService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/TownHall")
public class TownHallController {
    private final TownHallService townHallService;

    public TownHallController(TownHallService townHallService) {
        this.townHallService = townHallService;
    }

    @PostMapping("/add")
    public ResponseEntity<TownHall> createTownhall(@RequestBody TownHall townHall) {
        TownHall newTownHall= townHallService.createTownHall(townHall);
        return ResponseEntity.ok(newTownHall);
    }
}
