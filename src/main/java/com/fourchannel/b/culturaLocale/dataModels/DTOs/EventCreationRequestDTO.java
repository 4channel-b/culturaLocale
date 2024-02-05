package com.fourchannel.b.culturaLocale.dataModels.DTOs;

import com.fourchannel.b.culturaLocale.dataModels.Location;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EventCreationRequestDTO extends ContentCreationRequestDTO {
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Location location;
}
