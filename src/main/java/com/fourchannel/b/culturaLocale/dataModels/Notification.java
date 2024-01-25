package com.fourchannel.b.culturaLocale.dataModels;

import com.fourchannel.b.culturaLocale.dataModels.users.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name="notification")
public class Notification
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="notification_id_seq")
    private Long Id;
    private String title;
    private String description;
    private LocalDateTime timeStamp;

    public Notification(String title, String description)
    {
        if (title.isBlank() || description.isBlank())
        {
            throw new IllegalArgumentException("| ERROR | Title or Description are blank :(");
        }

        this.title = title;
        this.description = description;
        this.timeStamp = LocalDateTime.now();
    }
}
