package com.fourchannel.b.culturaLocale.dataModels;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
@Table(name="event")
public class Event extends Content {
    @Id
    private Long id;
    @Getter
    private Date startDate;
    @Getter
    private Date endDate;
    @ManyToOne
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
}
