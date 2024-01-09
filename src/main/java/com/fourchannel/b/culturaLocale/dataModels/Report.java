package com.fourchannel.b.culturaLocale.dataModels;

import java.util.Date;

import com.fourchannel.b.culturaLocale.dataModels.users.User;

public class Report {
    private String ID;
    private User reporter;
    private String description;
    private Date reportingDate;
    private int status;
    private String resolution;
    private Content content;
}