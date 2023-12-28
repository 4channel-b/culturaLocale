package com.fourchannel.b.culturaLocale.dataModels.users;

import com.fourchannel.b.culturaLocale.dataModels.IContent;
import com.fourchannel.b.culturaLocale.dataModels.users.IUser;

import java.util.Date;

public class Report {
    private String ID;
    private IUser reporter;
    private String description;
    private Date reportingDate;
    private int status;
    private String resolution;
    private IContent content;
}
