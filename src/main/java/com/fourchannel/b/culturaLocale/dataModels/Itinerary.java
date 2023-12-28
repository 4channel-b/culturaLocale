package com.fourchannel.b.culturaLocale.dataModels;

import com.fourchannel.b.culturaLocale.dataModels.users.IUser;

import java.util.Date;
import java.util.List;

public class Itinerary implements IContent {
    private String ID;
    private double estimatedDuration;
    private int difficultyLevel;
    private List<IContent> contents;

    /**
     * Retrieves the name of the itinerary.
     *
     * @return The name of the itinerary.
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Retrieves the description of the itinerary.
     *
     * @return The description of the itinerary.
     */
    @Override
    public String getDescription() {
        return this.description;
    }

    /**
     * Retrieves the creation date of the itinerary.
     *
     * @return The creation date of the itinerary.
     */
    @Override
    public Date getCreationDate() {
        return this.creationDate;
    }

    /**
     * Retrieves the set of images associated with the itinerary.
     *
     * @return The set of images.
     */
    @Override
    public ImageSet getImages() {
        return this.images;
    }

    /**
     * Retrieves the creator of the itinerary.
     *
     * @return The creator of the itinerary.
     */
    @Override
    public IUser getCreator() {
        return this.creator;
    }

    /**
     * Retrieves the approval status of the itinerary.
     *
     * @return The approval status.
     */
    @Override
    public ApprovalStatus getStatus() {
        return this.status;
    }

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
