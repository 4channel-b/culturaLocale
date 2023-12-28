package com.fourchannel.b.culturaLocale.dataModels;

import com.fourchannel.b.culturaLocale.dataModels.users.IUser;
import jakarta.persistence.Id;

import java.util.Date;

public class Event implements IContent {
    private String ID;
    private Date startDate;
    private Date endDate;
    private Location location;

    /**
     * Retrieves the name of the event.
     *
     * @return The name of the event.
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Retrieves the description of the event.
     *
     * @return The description of the event.
     */
    @Override
    public String getDescription() {
        return this.description;
    }

    /**
     * Retrieves the creation date of the event.
     *
     * @return The creation date of the event.
     */
    @Override
    public Date getCreationDate() {
        return this.creationDate;
    }

    /**
     * Retrieves the set of images associated with the event.
     *
     * @return The set of images.
     */
    @Override
    public ImageSet getImages() {
        return this.images;
    }

    /**
     * Retrieves the creator of the event.
     *
     * @return The creator of the event.
     */
    @Override
    public IUser getCreator() {
        return this.creator;
    }

    /**
     * Retrieves the approval status of the event.
     *
     * @return The approval status.
     */
    @Override
    public ApprovalStatus getStatus() {
        return this.status;
    }

    /**
     * Retrieves the content type of the event.
     *
     * @return The content type.
     */
    @Override
    public String getContentType() {
        return "EVENT";
    }

    public String getID() {
        return ID;
    }
}
