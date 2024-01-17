package com.fourchannel.b.culturaLocale.services.impl;

import com.fourchannel.b.culturaLocale.dataModels.Contest;
import com.fourchannel.b.culturaLocale.repositories.ContestRepository;
import com.fourchannel.b.culturaLocale.services.ContestService;
import org.springframework.stereotype.Service;

@Service
public class ContestServiceImpl implements ContestService {
    ContestRepository contestRepository;
    public ContestServiceImpl(ContestRepository contestRepository)
    {
        if(contestRepository == null)
        {
            throw new IllegalArgumentException("| ERROR | ContestRepository is NULL");
        }
        this.contestRepository=contestRepository;
    }
    @Override
    public Contest createContest(Contest contest)
    {
        if(contest == null)
        {
            throw new IllegalArgumentException("| ERROR | Contest is NULL");
        }
        return contestRepository.save(contest);
    }
}
