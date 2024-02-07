package com.fourchannel.b.culturaLocale.dataModels.users;

import com.fourchannel.b.culturaLocale.dataModels.DTOs.UserCreationRequestDTO;
import com.fourchannel.b.culturaLocale.dataModels.Notification;
import com.fourchannel.b.culturaLocale.dataModels.PointOfInterestCategory;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
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

    /**
     * Constructs a User entity based on a {@link UserCreationRequestDTO}, initializing it with provided user details.
     * Sets preferred category and notification list to null and suspended to false by default.
     *
     * @param userCreationRequestDTO The {@link UserCreationRequestDTO} containing user creation details.
     */
    public User(UserCreationRequestDTO userCreationRequestDTO) {
        this.username = userCreationRequestDTO.getUsername();
        this.fullName = userCreationRequestDTO.getFullName();
        this.email = userCreationRequestDTO.getEmail();
        this.registrationDate = userCreationRequestDTO.getRegistrationDate();

        this.preferredCategory = null;
        this.notificationList = null;
        this.suspended = false;
    }

    /**
     * Adds a notification to the user's list of notifications.
     *
     * @param n The {@link Notification} to be added to the user.
     */
    public void addNotification(Notification n) {
        this.notificationList.add(n);
    }
}
