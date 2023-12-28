package com.fourchannel.b.culturaLocale.dataModels;

import com.fourchannel.b.culturaLocale.dataModels.users.IUser;

import java.util.Date;
import java.util.List;

public class Itinerary extends Content {
    private String ID;
    private double estimatedDuration;
    private int difficultyLevel;
    private List<Content> contents;

    /**
     * Retrieves the content type of the itinerary.
     *
     * @return The content type.
     */
    @Override
    public String getContentType() {
        return "ITINERARY";
    }
}
