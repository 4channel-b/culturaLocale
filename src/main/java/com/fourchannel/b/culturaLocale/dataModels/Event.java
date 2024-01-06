package com.fourchannel.b.culturaLocale.dataModels;

import com.fourchannel.b.culturaLocale.dataModels.users.IUser;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Event extends Content {
    private String ID;
    private Date startDate;
    private Date endDate;
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

    public String getID() {
        return ID;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }
}
