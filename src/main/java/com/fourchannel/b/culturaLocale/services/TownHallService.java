package com.fourchannel.b.culturaLocale.services;

import com.fourchannel.b.culturaLocale.dataModels.Contest;
import com.fourchannel.b.culturaLocale.dataModels.TownHall;

import java.util.List;

public interface TownHallService {
    TownHall createTownHall(TownHall townHall);

    TownHall getById(Long aLong);

    List<TownHall> getAll();

    TownHall update(TownHall townHall, Long aLong);

    void delete(Long aLong);
}
