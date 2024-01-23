package com.fourchannel.b.culturaLocale.dataModels;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class PointOfInterest extends Content
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="pointOfInterest_id_seq")
    private Long Id;
    @Getter
    private PointOfInterestCategory category;
    @Getter
    @Embedded
    private Location location;
    @ManyToOne
    @JoinColumn(name = "town_hall_id")
    private TownHall townhall;

    /**
     * Retrieves the content type of the point of interest.
     *
     * @return The content type.
     */
    @Override
    public String getContentType() {
        return "POINT_OF_INTEREST";
    }
}
