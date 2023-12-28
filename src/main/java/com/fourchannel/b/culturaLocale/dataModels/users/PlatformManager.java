package com.fourchannel.b.culturaLocale.dataModels.users;

import com.fourchannel.b.culturaLocale.dataModels.TownHall;
import com.fourchannel.b.culturaLocale.repositories.IVectorRepository;

public class PlatformManager extends IUser {
    public void addTownHall(IVectorRepository<TownHall> repository, TownHall hall) {
        repository.add(hall);
    }
}