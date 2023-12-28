package com.fourchannel.b.culturaLocale.dataModels;

import com.fourchannel.b.culturaLocale.dataModels.users.IUser;

import java.util.Date;

public interface IContent {
    String name = null;
    String description = null;
    // GMT.
    Date creationDate = null;
    ImageSet images = null;
    IUser creator = null;
    ApprovalStatus status = null;

    String getName();
    String getDescription();
    Date getCreationDate();
    ImageSet getImages();
    IUser getCreator();
    ApprovalStatus getStatus();
    String getContentType(); // Needed to discern content types in class agnostic lists
}
