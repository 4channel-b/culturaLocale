package com.fourchannel.b.culturaLocale.dataModels;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Notification
{
    private String title;
    private String description;
    private LocalDateTime timeStamp;

    public Notification(String title, String description)
    {
        if(title.isBlank() || description.isBlank())
        {
            throw new IllegalArgumentException("| ERROR | Title or Description are blank :(");
        }
        this.title = title;
        this.description = description;
        this.timeStamp = LocalDateTime.now();
    }
}
