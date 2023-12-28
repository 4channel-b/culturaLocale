package com.fourchannel.b.culturaLocale.dataModels;

import com.fourchannel.b.culturaLocale.dataModels.users.IUser;

import java.util.Date;

public class PointOfInterest extends Content {
    private String ID;
    private int category;
    private Location location;
    private TownHall townHall;

    /**
     * Retrieves the content type of the point of interest.
     *
     * @return The content type.
     */
    @Override
    public String getContentType() {
        return "POINT_OF_INTEREST";
    }
}
