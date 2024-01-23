package com.fourchannel.b.culturaLocale.dataModels.DTOs;

import com.fourchannel.b.culturaLocale.dataModels.Content;
import jakarta.persistence.ManyToMany;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItineraryCreationRequestDTO extends ContentCreationRequestDTO{
    private double estimatedDuration;
    private int difficultyLevel;
    private List<Long> contents;
}
