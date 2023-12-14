package com.fourchannel.b.culturaLocale.dataModels;

import java.util.Date;

public class Itinerary implements IContent {
    private String ID;
    private String name;
    private String description;
    private User creator;
    private Date creationDate;
    private double estimatedDuration;
    private ApprovalStatus status;
    private int difficultyLevel;
}
