package com.fourchannel.b.culturaLocale.dataModels;

import java.util.List;

enum UserStatusEnum {
    Clear,
    Banned
}

public class User {
    private String ID;
    private String username;
    private String fullName;
    private String email;
    private UserStatusEnum registrationDate;
    private int status;
    private List<String> preferredCategories;
}
