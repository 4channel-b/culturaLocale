package com.fourchannel.b.culturaLocale.services.impl;

import com.fourchannel.b.culturaLocale.dataModels.TownHall;
import com.fourchannel.b.culturaLocale.dataModels.users.Role;
import com.fourchannel.b.culturaLocale.dataModels.users.TownHallRoleUser;
import com.fourchannel.b.culturaLocale.dataModels.users.User;
import com.fourchannel.b.culturaLocale.repositories.TownHallRepository;
import com.fourchannel.b.culturaLocale.repositories.UserRepository;
import com.fourchannel.b.culturaLocale.repositories.TownHallRoleRepository;
import com.fourchannel.b.culturaLocale.services.TownHallRoleService;
import com.fourchannel.b.culturaLocale.services.TownHallService;
import com.fourchannel.b.culturaLocale.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final TownHallRoleService townHallRoleService;
    private final TownHallRepository townHallRepository;

    public UserServiceImpl(UserRepository userRepository, TownHallRoleService townHallRoleService, TownHallRepository townHallRepository) {
        this.userRepository = userRepository;
        this.townHallRoleService = townHallRoleService;
        this.townHallRepository = townHallRepository;
    }

    @Override
    public User createUser(User user, TownHall townHall, Role role) {
        //TODO find townhall before insert in
        townHallRoleService.createTownHallRole(new TownHallRoleUser(null, townHall, role, user));
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }
}
