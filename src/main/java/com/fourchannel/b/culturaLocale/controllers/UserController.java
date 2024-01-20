package com.fourchannel.b.culturaLocale.controllers;

import com.fourchannel.b.culturaLocale.dataModels.TownHall;
import com.fourchannel.b.culturaLocale.dataModels.users.Role;
import com.fourchannel.b.culturaLocale.dataModels.users.TownHallRoleUser;
import com.fourchannel.b.culturaLocale.dataModels.users.User;
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
    public ResponseEntity<?> createUser(@RequestBody AddUserRequestWrapper addUserRequestWrapper)
    {
        User newUser = userService.createUser(addUserRequestWrapper.getUser(), addUserRequestWrapper.getTownHall(), addUserRequestWrapper.getRole());
        return ResponseEntity.ok(newUser);
    }
    @GetMapping("/getAll")
    public ResponseEntity<?> getAllUser()
    {
        return ResponseEntity.ok(userService.findAll());
    }
}
