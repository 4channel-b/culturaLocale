package com.fourchannel.b.culturaLocale.dataModels;

import com.fourchannel.b.culturaLocale.dataModels.DTOs.PointOfInterestCreationRequestDTO;
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

    public PointOfInterest(PointOfInterestCreationRequestDTO poiCreationRequestDTO) {
        super(
                poiCreationRequestDTO.getName(),
                poiCreationRequestDTO.getDescription(),
                poiCreationRequestDTO.getCreationDate(),
                poiCreationRequestDTO.getCreator()
        );

        this.category = poiCreationRequestDTO.getCategory();
        this.location = poiCreationRequestDTO.getLocation();
        this.townhall = new TownHall();
        this.townhall.setId(poiCreationRequestDTO.getTownHall());
    }
}
