package com.fourchannel.b.culturaLocale.services.impl;

import com.fourchannel.b.culturaLocale.dataModels.Contest;
import com.fourchannel.b.culturaLocale.repositories.ContestRepository;
import com.fourchannel.b.culturaLocale.services.ContestService;
import org.springframework.stereotype.Service;

@Service
public class ContestServiceImpl implements ContestService {
    ContestRepository contestRepository;
    public ContestServiceImpl(ContestRepository contestRepository){
        this.contestRepository=contestRepository;
    }
    @Override
    public Contest createContest(Contest contest) {
        return contestRepository.save(contest);
    }
}
