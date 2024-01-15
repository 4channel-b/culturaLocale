package com.fourchannel.b.culturaLocale.dataModels;

import java.time.LocalDateTime;

public class Notification
{
    private String title;
    private String description;
    private LocalDateTime timeStamp;

    public Notification(String title, String description)
    {
        this.title = title;
        this.description = description;
        this.timeStamp = LocalDateTime.now();
    }
}
