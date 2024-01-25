package com.fourchannel.b.culturaLocale.dataModels;

import com.fourchannel.b.culturaLocale.dataModels.DTOs.EventCreationRequestDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class Event extends Content {
    @Getter
    private Date startDate;
    @Getter
    private Date endDate;
    @Getter
    @Embedded
    private Location location;

    /**
     * Retrieves the content type of the event.
     *
     * @return The content type.
     */
    @Override
    public String getContentType() {
        return "EVENT";
    }

    // Constructor that takes EventCreationRequestDTO
    public Event(EventCreationRequestDTO eventCreationRequestDTO) {
        super(
                eventCreationRequestDTO.getName(),
                eventCreationRequestDTO.getDescription(),
                eventCreationRequestDTO.getCreationDate(),
                eventCreationRequestDTO.getCreator()
        );

        this.startDate = eventCreationRequestDTO.getStartDate();
        this.endDate = eventCreationRequestDTO.getEndDate();
        this.location = eventCreationRequestDTO.getLocation();
        this.townHall = new TownHall();
        this.townHall.setId(eventCreationRequestDTO.getTownHall());
    }
}
