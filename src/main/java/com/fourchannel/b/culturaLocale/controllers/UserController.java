package com.fourchannel.b.culturaLocale.controllers;

import com.fourchannel.b.culturaLocale.dataModels.DTOs.UserCreationRequestDTO;
import com.fourchannel.b.culturaLocale.dataModels.DTOs.UserSuspensionDTO;
import com.fourchannel.b.culturaLocale.dataModels.users.User;
import com.fourchannel.b.culturaLocale.mapper.UserMapper;
import com.fourchannel.b.culturaLocale.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> createUser(@RequestBody UserCreationRequestDTO dto)
    {
        User newUser = userService.createUser(UserMapper.INSTANCE.userDtoToUser(dto),
                                              dto.getTownHall(),
                                              dto.getRole());
        return ResponseEntity.ok(newUser);
    }
    @GetMapping("/getAll")
    public ResponseEntity<?> getAllUser()
    {
        return ResponseEntity.ok(userService.findAll());
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

    // TODO: Upgrade, downgrade <-- in their own controller
    // TODO: deletion, view
}
