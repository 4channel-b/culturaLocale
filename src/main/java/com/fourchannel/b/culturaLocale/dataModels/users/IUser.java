package com.fourchannel.b.culturaLocale.dataModels.users;

import java.util.Date;
import java.util.List;

public class IUser {
    private String ID;
    private String username;
    private String fullName;
    private String email;
    private Date registrationDate;
    private IUserStatusEnum status;
    private List<String> preferredCategories;
}