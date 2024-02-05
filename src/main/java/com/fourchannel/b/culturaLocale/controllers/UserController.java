package com.fourchannel.b.culturaLocale.controllers;

import com.fourchannel.b.culturaLocale.dataModels.DTOs.UserCreationRequestDTO;
import com.fourchannel.b.culturaLocale.dataModels.DTOs.UserRoleChangeDTO;
import com.fourchannel.b.culturaLocale.dataModels.DTOs.UserSuspensionDTO;
import com.fourchannel.b.culturaLocale.dataModels.users.User;
import com.fourchannel.b.culturaLocale.services.TownHallRoleService;
import com.fourchannel.b.culturaLocale.services.UserService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController implements BaseCrudController<UserCreationRequestDTO, Long> {
    private final UserService userService;
    private final TownHallRoleService townHallRoleService;

    public UserController(UserService userService, TownHallRoleService townHallRoleService) {
        this.userService = userService;
        this.townHallRoleService = townHallRoleService;
    }

    @PutMapping("/suspension/")
    public ResponseEntity<?> changeSuspensionStatus(@RequestBody UserSuspensionDTO dto) {
        try {
            userService.updateSuspensionStatus(dto.getId(), dto.isNewSuspensionStatus());

            return ResponseEntity.ok().body("Suspension change success.");
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PutMapping("/role/")
    public ResponseEntity<?> changeRole(@RequestBody UserRoleChangeDTO dto) {
        try {
            if (townHallRoleService.getRole(dto.getId(), dto.getTownHallId()) == dto.getNewRole()) {
                return ResponseEntity.badRequest().body("User already has this role.");
            }

            townHallRoleService.setRole(dto.getId(), dto.getTownHallId(), dto.getNewRole());

            return ResponseEntity.ok().body("Role change success.");
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> create(UserCreationRequestDTO dto) {
        User newUser = userService.createUser(new User(dto),
                dto.getTownHall(),
                dto.getRole());
        return ResponseEntity.ok(newUser);
    }

    @Override
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.getUser(id));
    }

    @Override
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @Override
    public ResponseEntity<?> update(UserCreationRequestDTO entity, Long aLong) {
        // Bad request. No update use case for users yet.
        return ResponseEntity.badRequest().body("Not implemented.");
    }

    @Override
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        userService.delete(id);

        return ResponseEntity.ok().body("User successfully deleted.");
    }
}
