package com.fourchannel.b.culturaLocale.controllers;

import com.fourchannel.b.culturaLocale.dataModels.Contest;
import com.fourchannel.b.culturaLocale.services.ContestService;
import com.fourchannel.b.culturaLocale.services.impl.ContestServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/contest")
public class ContestController implements BaseCrudController<Contest, Long> {
    private final ContestService contestService;

    public ContestController(ContestService contestService) {
        this.contestService = contestService;
    }

    @Override
    public ResponseEntity<Contest> create(Contest entity) {
        return ResponseEntity.ok(contestService.createContest(entity));
    }

    @Override
    public ResponseEntity<Contest> getById(Long id) {
        return ResponseEntity.ok(contestService.getContest(id)); //Da rivedere

    }

    @Override
    public ResponseEntity<List<Contest>> getAll() {
        return ResponseEntity.ok(contestService.getAllContest());
    }

    @Override
    public ResponseEntity<?> update(Contest entity) {
        contestService.updateContest(entity);
        return ResponseEntity.ok("{}");
    }

    @Override
    public ResponseEntity<?> delete(Long aLong) {
        return null;
    }
}