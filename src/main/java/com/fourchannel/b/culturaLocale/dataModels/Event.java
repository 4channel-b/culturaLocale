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
    private Date startDate;
    private Date endDate;
    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name="location_id")
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



    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }
}
