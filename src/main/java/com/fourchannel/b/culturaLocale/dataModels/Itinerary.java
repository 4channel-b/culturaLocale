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
}
