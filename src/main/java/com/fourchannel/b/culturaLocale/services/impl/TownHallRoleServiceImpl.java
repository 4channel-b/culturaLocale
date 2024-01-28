package com.fourchannel.b.culturaLocale.services.impl;

import com.fourchannel.b.culturaLocale.dataModels.users.Role;
import com.fourchannel.b.culturaLocale.dataModels.users.TownHallRoleUser;
import com.fourchannel.b.culturaLocale.repositories.TownHallRoleRepository;
import com.fourchannel.b.culturaLocale.services.TownHallRoleService;
import com.fourchannel.b.culturaLocale.services.TownHallService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TownHallRoleServiceImpl implements TownHallRoleService {
   private final TownHallRoleRepository townHallRoleRepository;
   private final TownHallService townHallService;
    public TownHallRoleServiceImpl(TownHallRoleRepository townHallRoleRepository,
                                   TownHallService townHallService) {
        this.townHallRoleRepository = townHallRoleRepository;
        this.townHallService = townHallService;
    }

    @Override
    public TownHallRoleUser createTownHallRole(TownHallRoleUser townHallRoleUser) {
        for (TownHallRoleUser i : townHallRoleRepository.findAll()) {
            if (i.getTownHall().equals(townHallRoleUser.getTownHall()) &&
                    i.getUser().equals(townHallRoleUser.getUser())) {
                throw new IllegalArgumentException("the user has already got a role in this townhall");
            }
        }
        return townHallRoleRepository.save(townHallRoleUser);
    }

    @Override
    public Role getRole(Long userId, Long townHallId) {
        List<TownHallRoleUser> roles = townHallRoleRepository.findTownHallRolesByUserId(userId);

        for (TownHallRoleUser i : roles) {
            if (i.getTownHall().getId().equals(townHallId)) {
                // throw if the townHall does not exist
                townHallService.getById(townHallId);

                return i.getRole();
            }
        }

        throw new IllegalArgumentException("User does not have a role in this townhall");
    }

    @Override
    public void setRole(Long userId, Long townHallId, Role role) {
        List<TownHallRoleUser> roles = townHallRoleRepository.findTownHallRolesByUserId(userId);

        for (TownHallRoleUser i : roles) {
            if (i.getTownHall().getId().equals(townHallId)) {
                // throw if the townHall does not exist
                townHallService.getById(townHallId);

                i.setRole(role);
                townHallRoleRepository.save(i);
                return;
            }
        }

        throw new IllegalArgumentException("User does not have a role in this townHall");
    }
}
