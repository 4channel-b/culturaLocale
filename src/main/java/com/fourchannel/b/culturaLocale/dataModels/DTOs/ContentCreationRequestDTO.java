package com.fourchannel.b.culturaLocale.dataModels.DTOs;

import com.fourchannel.b.culturaLocale.dataModels.ApprovalStatus;
import com.fourchannel.b.culturaLocale.dataModels.users.User;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ContentCreationRequestDTO {
    private String name;
    private String description;
    private Date creationDate;
    private Long creator;
    private Long townHall;
}
