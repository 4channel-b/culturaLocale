package com.fourchannel.b.culturaLocale.dataModels;

import com.fourchannel.b.culturaLocale.dataModels.users.User;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

public abstract class Content {
    private String Id;
    @Getter
    String name = null;
    @Getter
    String description = null;
    // GMT.
    @Getter
    Date creationDate = null;
    @Getter
    ImageSet images = null;
    @Getter
    User creator = null;
    @Getter
    @Setter
    ApprovalStatus status = null;

    public abstract String getContentType(); // Needed to discern content types in class agnostic lists
}
