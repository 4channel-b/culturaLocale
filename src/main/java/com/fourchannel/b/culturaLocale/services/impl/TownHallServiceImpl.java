package com.fourchannel.b.culturaLocale.services.impl;

import com.fourchannel.b.culturaLocale.dataModels.TownHall;
import com.fourchannel.b.culturaLocale.repositories.TownHallRepository;
import com.fourchannel.b.culturaLocale.services.TownHallService;
import org.springframework.stereotype.Service;

@Service
public class TownHallServiceImpl implements TownHallService{
    private final TownHallRepository townHallRepository;

    public TownHallServiceImpl(TownHallRepository townHallRepository) {
        this.townHallRepository = townHallRepository;
    }

    @Override
    public TownHall createTownHall(TownHall townHall) {
        return townHallRepository.save(townHall);
    }
}
