package com.fourchannel.b.culturaLocale.dataModels;

import com.fourchannel.b.culturaLocale.dataModels.users.User;

import java.util.Date;
import com.fourchannel.b.culturaLocale.dataModels.users.User;

public abstract class Content {
    private String Id;
    String name = null;
    String description = null;
    // GMT.
    Date creationDate = null;
    ImageSet images = null;
    User creator = null;
    ApprovalStatus status = null;

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public ImageSet getImages() {
        return this.images;
    }

    public User getCreator() {
        return this.creator;
    }

    public ApprovalStatus getStatus() {
        return this.status;
    }

    public void setStatus(ApprovalStatus status) {
        this.status = status;
    }

    public abstract String getContentType(); // Needed to discern content types in class agnostic lists
}
