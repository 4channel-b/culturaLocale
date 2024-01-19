package com.fourchannel.b.culturaLocale.dataModels;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
@Table(name="itinerary")
public class Itinerary extends Content {
    @Id
    private Long id;
    private double estimatedDuration;
    private int difficultyLevel;
    @ManyToMany
    private List<PointOfInterest> contents;


    /**
     * Retrieves the content type of the itinerary.
     *
     * @return The content type.
     */
    @Override
    public String getContentType() {
        return "ITINERARY";
    }

    public int getDifficultyLevel() {
        return difficultyLevel;
    }
}
