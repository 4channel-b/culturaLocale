package com.fourchannel.b.culturaLocale.dataModels.DTOs;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItineraryCreationRequestDTO extends ContentCreationRequestDTO {
    private LocalDateTime estimatedDuration;
    private int difficultyLevel;
    private List<Long> contents;
}
