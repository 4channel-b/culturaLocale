package com.fourchannel.b.culturaLocale.services.impl;

import com.fourchannel.b.culturaLocale.dataModels.TownHall;
import com.fourchannel.b.culturaLocale.repositories.TownHallRepository;
import com.fourchannel.b.culturaLocale.services.TownHallService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TownHallServiceImpl implements TownHallService{
    private final TownHallRepository townHallRepository;

    public TownHallServiceImpl(TownHallRepository townHallRepository)
    {
        if(townHallRepository == null) {
            throw new IllegalArgumentException("| ERROR | TownHallRepository is NULL");
        }

        this.townHallRepository = townHallRepository;
    }

    @Override
    public TownHall createTownHall(TownHall townHall)
    {
        if (townHall == null) {
            throw new IllegalArgumentException("| ERROR | TownHall is NULL");
        }

        return townHallRepository.save(townHall);
    }

    @Override
    public TownHall getById(Long aLong) {
        return townHallRepository.findById(aLong)
                .orElseThrow(() -> new IllegalArgumentException("| ERROR | TownHall not found"));
    }

    @Override
    public List<TownHall> getAll() {
        return StreamSupport.stream(townHallRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public TownHall update(TownHall townHall, Long aLong) {
        townHallRepository.findById(aLong)
                .orElseThrow(() -> new IllegalArgumentException("| ERROR | TownHall not found"));
        townHall.setId(aLong);
        townHallRepository.save(townHall);

        return townHall;
    }

    @Override
    public void delete(Long aLong) {
        townHallRepository.deleteById(aLong);
    }
}
