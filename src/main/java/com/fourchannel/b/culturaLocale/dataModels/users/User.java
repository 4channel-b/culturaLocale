package com.fourchannel.b.culturaLocale.dataModels.users;

import com.fourchannel.b.culturaLocale.dataModels.Notification;
import com.fourchannel.b.culturaLocale.dataModels.PointOfInterestCategory;

import java.util.Date;
import java.util.List;

public class User {
    private String id, username, fullName, email;
    private Date registrationDate;
    private PointOfInterestCategory preferredCategory;
    private List<Notification> notificationList;
}
