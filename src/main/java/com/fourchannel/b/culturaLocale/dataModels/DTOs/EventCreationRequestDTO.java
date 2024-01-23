package com.fourchannel.b.culturaLocale.dataModels.DTOs;

import com.fourchannel.b.culturaLocale.dataModels.Location;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EventCreationRequestDTO extends ContentCreationRequestDTO{
    private Date startDate;

    private Date endDate;

    private Location location;
}
