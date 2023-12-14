package com.fourchannel.b.culturaLocale.dataModels;

import java.util.Date;

public class Report {
    private String ID;
    private User reporter; // Assuming User is a class you have defined elsewhere
    private String description;
    private Date reportingDate;
    private int status;
    private String resolution;
    private IContent content; // Assuming Content is a class you have defined elsewhere
}
