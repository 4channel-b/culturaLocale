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

    /**
     * Creates a new user with a specified role in a town hall. Validates the existence of the town hall and the validity of the role.
     * Throws IllegalArgumentException if the town hall does not exist, the role is invalid, or the user already exists.
     *
     * @param user The user to be created.
     * @param townHall The ID of the town hall where the user will have a role.
     * @param role The index of the role from the Role enum.
     * @return The created user entity.
     * @throws IllegalArgumentException if town hall does not exist, role is invalid, or user already exists.
     */
    @Override
    public User createUser(User user, Long townHall, int role) {
        if (!this.townHallRepository.existsById(townHall)) {
            throw new IllegalArgumentException("| ERROR | TownHall does not exist");
        }

        if (role < 0 || role > Role.values().length) {
            throw new IllegalArgumentException("| ERROR | Role does not exist");
        }

        if (userRepository.exists(user.getUsername(), user.getEmail())) {
            throw new IllegalArgumentException("| ERROR | User already exists");
        }

        User newUser = userRepository.save(user);

        townHallRoleService.createTownHallRole(new TownHallRoleUser(null, townHallRepository.findById(townHall).get(),
                                                Role.values()[role],  newUser));
        return newUser;
    }

    /**
     * Updates the suspension status of a user. Validates the existence of the user.
     *
     * @param id The ID of the user whose suspension status is to be updated.
     * @param newStatus The new suspension status.
     * @throws IllegalArgumentException if the user does not exist.
     */
    @Override
    public void updateSuspensionStatus(Long id, boolean newStatus) {
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("| ERROR | User does not exist.");
        }

        User user = userRepository.findById(id).get();
        user.setSuspended(newStatus);
        userRepository.save(user);
    }

    /**
     * Retrieves all users available in the system.
     *
     * @return A list of all users.
     */
    @Override
    public List<User> findAll() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    /**
     * Deletes a user by their ID. Validates the existence of the user before deletion.
     *
     * @param id The ID of the user to be deleted.
     * @throws IllegalArgumentException if the user does not exist.
     */
    @Override
    public void delete(Long id) {
        if(!userRepository.existsById(id)) {
            throw new IllegalArgumentException("| ERROR | ID does not exist");
        }

        userRepository.delete(userRepository.findById(id).get());
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param id The ID of the user to retrieve.
     * @return The retrieved user entity.
     */
    @Override
    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow();
    }
}
