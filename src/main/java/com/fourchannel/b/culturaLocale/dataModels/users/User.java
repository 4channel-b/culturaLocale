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

    public User(UserCreationRequestDTO userCreationRequestDTO) {
        this.username = userCreationRequestDTO.getUsername();
        this.fullName = userCreationRequestDTO.getFullName();
        this.email = userCreationRequestDTO.getEmail();
        this.registrationDate = userCreationRequestDTO.getRegistrationDate();

        this.preferredCategory = null;
        this.notificationList = null;
        this.suspended = false;
    }
}
