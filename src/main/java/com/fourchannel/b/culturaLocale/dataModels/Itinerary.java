package com.fourchannel.b.culturaLocale.dataModels;

import com.fourchannel.b.culturaLocale.dataModels.DTOs.ItineraryCreationRequestDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class Itinerary extends Content {

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

    /**
     * Constructs an Itinerary entity based on an {@link ItineraryCreationRequestDTO}.
     *
     * @param itineraryCreationRequestDTO The {@link ItineraryCreationRequestDTO} containing itinerary creation details.
     */
    public Itinerary(ItineraryCreationRequestDTO itineraryCreationRequestDTO) {
        super(
                itineraryCreationRequestDTO.getName(),
                itineraryCreationRequestDTO.getDescription(),
                itineraryCreationRequestDTO.getCreationDate(),
                itineraryCreationRequestDTO.getCreator()
        );


        this.difficultyLevel = itineraryCreationRequestDTO.getDifficultyLevel();
        this.contents = new ArrayList<>();
        this.townHall = new TownHall();
        this.townHall.setId(itineraryCreationRequestDTO.getTownHall());
    }
}
