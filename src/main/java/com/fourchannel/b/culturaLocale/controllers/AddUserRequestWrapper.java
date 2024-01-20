package com.fourchannel.b.culturaLocale.controllers;

import com.fourchannel.b.culturaLocale.dataModels.TownHall;
import com.fourchannel.b.culturaLocale.dataModels.users.Role;
import com.fourchannel.b.culturaLocale.dataModels.users.User;
import lombok.Getter;


public class AddUserRequestWrapper {
    @Getter
    User user;
    @Getter
    TownHall townHall;
    @Getter
    Role role;
}
