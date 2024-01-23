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


    String name;
    String description;
    Date creationDate;
    Long creator ;
}
