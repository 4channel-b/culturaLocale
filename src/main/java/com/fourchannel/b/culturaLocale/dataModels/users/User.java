package com.fourchannel.b.culturaLocale.dataModels.users;

import com.fourchannel.b.culturaLocale.dataModels.Notification;
import com.fourchannel.b.culturaLocale.dataModels.PointOfInterestCategory;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
@Table(name="user")
public class User {
    @Id
    private Long Id;
    private String username=null, fullName=null, email=null;
    private Date registrationDate=null;
    private PointOfInterestCategory preferredCategory=null;
    @ManyToMany
    @JoinTable(
            name = "user_notification",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "notification_id")
    )
    private List<Notification> notificationList=null;

}
