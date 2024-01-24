package com.fourchannel.b.culturaLocale.dataModels.DTOs;

import com.fourchannel.b.culturaLocale.dataModels.Location;
import com.fourchannel.b.culturaLocale.dataModels.PointOfInterestCategory;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PointOfInterestCreationRequestDTO extends ContentCreationRequestDTO {
    private PointOfInterestCategory category;
    private Location location;
    private Long townHall;
}
