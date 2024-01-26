package com.fourchannel.b.culturaLocale.services.impl;

import com.fourchannel.b.culturaLocale.dataModels.users.TownHallRoleUser;
import com.fourchannel.b.culturaLocale.repositories.TownHallRoleRepository;
import com.fourchannel.b.culturaLocale.services.TownHallRoleService;
import org.springframework.stereotype.Service;

@Service
public class TownHallRoleServiceImpl implements TownHallRoleService {
   private final  TownHallRoleRepository townHallRoleRepository;

    public TownHallRoleServiceImpl(TownHallRoleRepository townHallRoleRepository) {
        this.townHallRoleRepository = townHallRoleRepository;
    }

    @Override
    public TownHallRoleUser createTownHallRole(TownHallRoleUser townHallRoleUser) {
        for(TownHallRoleUser i : townHallRoleRepository.findAll()){
            if(i.getTownHall().equals(townHallRoleUser.getTownHall())&& i.getUser().equals(townHallRoleUser.getUser()))
                throw new IllegalArgumentException("the user has already got a role in this townhall");
        }
        return townHallRoleRepository.save(townHallRoleUser);
    }
}
