package com.fourchannel.b.culturaLocale.dataModels;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
public class PointOfInterest extends Content
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="pointOfInterest_id_seq")
    private Long Id;
    @Getter
    private PointOfInterestCategory category;
    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;
    @ManyToOne
    @JoinColumn(name = "town_hall_id")
    private TownHall townHall;

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
