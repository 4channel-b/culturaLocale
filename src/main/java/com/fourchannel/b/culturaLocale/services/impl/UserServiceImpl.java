package com.fourchannel.b.culturaLocale.services.impl;

import com.fourchannel.b.culturaLocale.dataModels.users.Role;
import com.fourchannel.b.culturaLocale.dataModels.users.TownHallRoleUser;
import com.fourchannel.b.culturaLocale.dataModels.users.User;
import com.fourchannel.b.culturaLocale.repositories.TownHallRepository;
import com.fourchannel.b.culturaLocale.repositories.UserRepository;
import com.fourchannel.b.culturaLocale.services.TownHallRoleService;
import com.fourchannel.b.culturaLocale.services.TownHallService;
import com.fourchannel.b.culturaLocale.services.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final TownHallRoleService townHallRoleService;
    private final TownHallRepository townHallRepository;
    private final TownHallService townHallService;

    public UserServiceImpl(UserRepository userRepository, TownHallRoleService townHallRoleService, TownHallRepository townHallRepository, TownHallService townHallService) {
        this.userRepository = userRepository;
        this.townHallRoleService = townHallRoleService;
        this.townHallRepository = townHallRepository;
        this.townHallService = townHallService;
    }

    @Override
    public User createUser(User user, Long townHall, int role) {
        if(!this.townHallRepository.existsById(townHall)) {
            throw new IllegalArgumentException("| ERROR | TownHall does not exist");
        }

        if (role < 0 || role > Role.values().length) {
            throw new IllegalArgumentException("| ERROR | Role does not exist");
        }

        if (userRepository.exists(user.getUsername(), user.getEmail())) {
            throw new IllegalArgumentException("| ERROR | User does not exist");
        }

        User newUser = userRepository.save(user);

        townHallRoleService.createTownHallRole(new TownHallRoleUser(null, townHallRepository.findById(townHall).get(),
                                                Role.values()[role],  newUser));
        return newUser;
    }

    @Override
    public List<User> findAll() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }
}
