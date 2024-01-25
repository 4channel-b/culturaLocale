package com.fourchannel.b.culturaLocale.dataModels;

import com.fourchannel.b.culturaLocale.dataModels.DTOs.ItineraryCreationRequestDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class Itinerary extends Content {

    private double estimatedDuration;
    @Getter
    private int difficultyLevel;
    @ManyToMany
    private List<Content> contents;


    /**
     * Retrieves the content type of the itinerary.
     *
     * @return The content type.
     */
    @Override
    public String getContentType() {
        return "ITINERARY";
    }

    public Itinerary(ItineraryCreationRequestDTO itineraryCreationRequestDTO) {
        super(
                itineraryCreationRequestDTO.getName(),
                itineraryCreationRequestDTO.getDescription(),
                itineraryCreationRequestDTO.getCreationDate(),
                null
        );

        this.estimatedDuration = itineraryCreationRequestDTO.getEstimatedDuration();
        this.difficultyLevel = itineraryCreationRequestDTO.getDifficultyLevel();
        this.contents = new ArrayList<>();
        this.townHall = new TownHall();
        this.townHall.setId(itineraryCreationRequestDTO.getTownHall());
    }
}
