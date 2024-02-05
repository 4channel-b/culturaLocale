package com.fourchannel.b.culturaLocale.services;

import com.fourchannel.b.culturaLocale.dataModels.users.Role;
import com.fourchannel.b.culturaLocale.dataModels.users.TownHallRoleUser;

public interface TownHallRoleService {
    TownHallRoleUser createTownHallRole(TownHallRoleUser townHallRoleUser);
    Role getRole(Long userId, Long townHallId);
    void setRole(Long userId, Long townHallId, Role role);
}
