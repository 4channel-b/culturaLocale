package com.fourchannel.b.culturaLocale.dataModels;

import com.fourchannel.b.culturaLocale.dataModels.users.User;

import java.time.LocalDateTime;
import java.util.Date;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jdk.jshell.spi.ExecutionControl;
import lombok.Getter;
import lombok.Setter;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.TimeZoneColumn;
import org.hibernate.annotations.TimeZoneStorageType;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="content")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="content_id_seq")
    private Long Id;
    String name = null;
    String description = null;
    // GMT
    Date creationDate = null;
    @ManyToOne
    User creator = null;
    ApprovalStatus status = null;
    @ManyToOne
    TownHall townHall;

    private LocalDateTime estimatedDuration;

    public String getContentType() throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("Not implemented yet.");
    }

    public Content(String name, String description, Date creationDate, Long creator) {
        this.name = name;
        this.description = description;
        this.creationDate = creationDate;
        this.creator = new User();
        this.creator.setId(creator);
        this.estimateDurationInheritance();
    }

    public Content(Long id) {
        this.setId(id);
    }

    public boolean isExpired()
    {
        LocalDateTime actualTime = LocalDateTime.now();
        return this.estimatedDuration.isAfter(actualTime);
    }

    //Set ten min from now that represent expiration time
    private void estimateDurationInheritance()
    {
        //Created a LocalDateTime elem
        LocalDateTime elem = LocalDateTime.now();

        //Time till expirations are 10 min
        //Set into estimateDuration
        //TODO: if anyone wants just replace 10 w/ something else
        this.estimatedDuration = elem.plusMinutes(10);
    }
}
