package com.fourchannel.b.culturaLocale.services.impl;

import com.fourchannel.b.culturaLocale.dataModels.Content;
import com.fourchannel.b.culturaLocale.dataModels.Contest;
import com.fourchannel.b.culturaLocale.repositories.ContentRepository;
import com.fourchannel.b.culturaLocale.repositories.ContestRepository;
import com.fourchannel.b.culturaLocale.services.ContestService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ContestServiceImpl implements ContestService {
    private final ContestRepository contestRepository;
    private final ContentRepository contentRepository;
    public ContestServiceImpl(ContestRepository contestRepository, ContentRepository contentRepository)
    {
        this.contestRepository=contestRepository;
        this.contentRepository = contentRepository;
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

    public Contest getContest(Long id)
    {
        return this.contestRepository.findById(id).orElse(null);
    }

    @Override
    public List<Contest> getAllContest()
    {
        return StreamSupport.stream(contestRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public void updateContest(Contest contest, List<Long> contents)
    {
        if (contest == null) {
            throw new IllegalArgumentException("| ERROR | Contest is NULL");
        }

        //ID's check
        contestRepository.findById(contest.getId())
                .orElseThrow(() -> new IllegalArgumentException("| ERROR | Contest doesn't exist"));


        for (Long id : contents)
        {
            Content elem = this.contentRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("| ERROR | Content doesn't exist"));
            contest.getContents().add(elem);
        }

        //Assicurami id esistente

        contestRepository.save(contest);
    }
}
