package com.fourchannel.b.culturaLocale.dataModels;

import java.util.Date;

public class PointOfInterest implements IContent {
    private String ID;
    private int category;
    private User createdBy;
    private Date creationDate;
    private String description;
    private Location location;
    private String name;
    private ApprovalStatus status;
    private Town town;
}
