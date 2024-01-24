package com.fourchannel.b.culturaLocale.dataModels;

import com.fourchannel.b.culturaLocale.dataModels.DTOs.ItineraryCreationRequestDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class Itinerary extends Content {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="itinerary_id_seq")
    private Long Id;
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
                itineraryCreationRequestDTO.getCreator()
        );

        this.estimatedDuration = itineraryCreationRequestDTO.getEstimatedDuration();
        this.difficultyLevel = itineraryCreationRequestDTO.getDifficultyLevel();

        // TODO: Fix compilation error here, also review Itinerary's design?
        // this.contents = itineraryCreationRequestDTO.getContents().stream()
        //        .map(Content::new)
        //        .collect(Collectors.toList());
    }
}
