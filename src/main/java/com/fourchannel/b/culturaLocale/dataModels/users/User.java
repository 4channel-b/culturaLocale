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
@Setter
@Entity
@Table(name="app_users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="user_id_seq")
    private Long id;
    private String username;
    private String fullName;
    private String email;
    private Date registrationDate;
    private PointOfInterestCategory preferredCategory;
    @OneToMany
    private List<Notification> notificationList;
    private boolean suspended;
}
