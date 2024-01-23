package com.fourchannel.b.culturaLocale.dataModels.DTOs;

import com.fourchannel.b.culturaLocale.dataModels.Location;
import com.fourchannel.b.culturaLocale.dataModels.PointOfInterestCategory;
import com.fourchannel.b.culturaLocale.dataModels.TownHall;
import jakarta.persistence.*;
import lombok.*;
import org.mapstruct.Mapping;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PoiCreationRequestDTO extends ContentCreationRequestDTO{

    private int category;

    private Location location;

    private Long townHall;
}
