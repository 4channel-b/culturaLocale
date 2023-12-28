package com.fourchannel.b.culturaLocale.dataModels;

import com.fourchannel.b.culturaLocale.dataModels.users.IUser;

import java.util.Date;

public class PointOfInterest implements IContent {
    private String ID;
    private int category;
    private Location location;
    private TownHall townHall;

    /**
     * Retrieves the name of the point of interest.
     *
     * @return The name of the point of interest.
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Retrieves the description of the point of interest.
     *
     * @return The description of the point of interest.
     */
    @Override
    public String getDescription() {
        return this.description;
    }

    /**
     * Retrieves the creation date of the point of interest.
     *
     * @return The creation date of the point of interest.
     */
    @Override
    public Date getCreationDate() {
        return this.creationDate;
    }

    /**
     * Retrieves the set of images associated with the point of interest.
     *
     * @return The set of images.
     */
    @Override
    public ImageSet getImages() {
        return this.images;
    }

    /**
     * Retrieves the creator of the point of interest.
     *
     * @return The creator of the point of interest.
     */
    @Override
    public IUser getCreator() {
        return this.creator;
    }

    /**
     * Retrieves the approval status of the point of interest.
     *
     * @return The approval status.
     */
    @Override
    public ApprovalStatus getStatus() {
        return this.status;
    }

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
