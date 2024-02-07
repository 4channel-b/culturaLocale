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

    /**
     * Creates a new TownHallRoleUser association.
     * Validates that the user does not already have a role in the specified town hall.
     * Throws IllegalArgumentException if the user already has a role in the specified town hall.
     *
     * @param townHallRoleUser The TownHallRoleUser entity to be created.
     * @return The saved TownHallRoleUser entity.
     * @throws IllegalArgumentException if the user already has a role in the specified town hall.
     */
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

    /**
     * Retrieves the role of a user in a specific town hall. Validates the existence of the town hall.
     * Throws IllegalArgumentException if the user does not have a role in the specified town hall.
     *
     * @param userId The ID of the user whose role is to be retrieved.
     * @param townHallId The ID of the town hall.
     * @return The Role of the user in the specified town hall.
     * @throws IllegalArgumentException if the user does not have a role in the specified town hall.
     */
    @Override
    public Role getRole(Long userId, Long townHallId) {
        List<TownHallRoleUser> roles = townHallRoleRepository.findTownHallRolesByUserId(userId);

        for (TownHallRoleUser i : roles) {
            if (i.getTownHall().getId().equals(townHallId)) {
                townHallService.getById(townHallId);

                return i.getRole();
            }
        }

        throw new IllegalArgumentException("User does not have a role in this townhall");
    }

    /**
     * Sets a new role for a user in a specific town hall. Validates the existence of the town hall and the user's role.
     * Updates the user's role if it exists, otherwise throws IllegalArgumentException.
     *
     * @param userId The ID of the user whose role is to be set.
     * @param townHallId The ID of the town hall where the role is to be set.
     * @param role The new role to be assigned to the user.
     * @throws IllegalArgumentException if the user does not have a role in the specified town hall.
     */
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
