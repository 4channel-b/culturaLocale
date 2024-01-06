package com.fourchannel.b.culturaLocale.dataModels;

import com.fourchannel.b.culturaLocale.dataModels.users.IUser;
import lombok.*;

import java.util.Date;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Itinerary extends Content {


    private String ID;
    private double estimatedDuration;
    private int difficultyLevel;
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

    public int getDifficultyLevel() {
        return difficultyLevel;
    }
}
