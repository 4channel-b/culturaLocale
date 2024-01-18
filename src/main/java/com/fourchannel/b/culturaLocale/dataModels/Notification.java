package com.fourchannel.b.culturaLocale.dataModels;

import com.fourchannel.b.culturaLocale.dataModels.users.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
@Table(name="notification")
public class Notification
{
    @Id
    private Long Id;
    private String title;
    private String description;
    private LocalDateTime timeStamp;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

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
