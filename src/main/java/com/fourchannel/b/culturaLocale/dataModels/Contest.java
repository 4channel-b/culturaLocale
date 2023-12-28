package com.fourchannel.b.culturaLocale.dataModels;


import java.util.Date;
import java.util.List;

public class Contest {
    private Long Id;
    private String name;
    private String description;
    private Date initialDate;
    private Date endDate;
    private String rules;
    private String type;
    private List<Content> contents;

    public String getType() {
        return type;
    }
}
