package com.fourchannel.b.culturaLocale.controllers;

import com.fourchannel.b.culturaLocale.dataModels.Contest;
import com.fourchannel.b.culturaLocale.dataModels.DTOs.ContestCreationRequestDTO;
import com.fourchannel.b.culturaLocale.services.ContestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/contest")
public class ContestController implements BaseCrudController<ContestCreationRequestDTO, Long> {
    private final ContestService contestService;

    public ContestController(ContestService contestService) {
        this.contestService = contestService;
    }

    @Override
    public ResponseEntity<Contest> create(ContestCreationRequestDTO dto) {
        Contest elem = contestService.createContest(new Contest(dto));

        return ResponseEntity.ok(elem);
    }

    @Override
    public ResponseEntity<Contest> getById(Long id) {
        return ResponseEntity.ok(contestService.getContest(id));
    }

    @Override
    public ResponseEntity<List<Contest>> getAll() {
        return ResponseEntity.ok(contestService.getAllContest());
    }

    @Override
    public ResponseEntity<?> update(ContestCreationRequestDTO dto, Long id) {
        Contest elem = new Contest(dto);
        elem.setId(id);

        this.contestService.updateContest(elem, dto.getContents());

        return ResponseEntity.ok("{}");
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        return null;
    }
}