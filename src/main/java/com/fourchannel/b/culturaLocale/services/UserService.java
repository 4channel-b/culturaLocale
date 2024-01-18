package com.fourchannel.b.culturaLocale.services;

import com.fourchannel.b.culturaLocale.dataModels.Contest;
import com.fourchannel.b.culturaLocale.dataModels.users.User;

import java.util.List;

public interface UserService {
    public User createUser(User user);
    public List<User> findAll();
}
