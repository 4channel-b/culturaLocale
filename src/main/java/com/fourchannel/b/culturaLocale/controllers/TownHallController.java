package com.fourchannel.b.culturaLocale.controllers;

import com.fourchannel.b.culturaLocale.dataModels.DTOs.TownHallCreationRequestDTO;
import com.fourchannel.b.culturaLocale.dataModels.TownHall;
import com.fourchannel.b.culturaLocale.services.TownHallService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/townHall")
public class TownHallController implements BaseCrudController<TownHallCreationRequestDTO, Long> {
    private final TownHallService townHallService;

    public TownHallController(TownHallService townHallService) {
        this.townHallService = townHallService;
    }

    @Override
    public ResponseEntity<TownHall> create(@RequestBody TownHallCreationRequestDTO dto) {
        TownHall newTownHall = townHallService.createTownHall(new TownHall(dto));
        return ResponseEntity.ok(newTownHall);
    }

    @Override
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(townHallService.getById(id));
    }

    @Override
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(townHallService.getAll());
    }

    @Override
    public ResponseEntity<?> update(TownHallCreationRequestDTO entity, Long aLong) {
        return ResponseEntity.ok(townHallService.update(new TownHall(entity), aLong));
    }

    @Override
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        townHallService.delete(id);
        return ResponseEntity.ok("{}");
    }
}
