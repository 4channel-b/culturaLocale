package com.fourchannel.b.culturaLocale.dataModels;

import com.fourchannel.b.culturaLocale.dataModels.users.User;

import java.util.Date;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jdk.jshell.spi.ExecutionControl;
import lombok.Getter;
import lombok.Setter;


import jakarta.persistence.*;
import lombok.*;

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
    @Getter
    String name = null;
    @Getter
    String description = null;
    // GMT.
    @Getter
    Date creationDate = null;
    @ManyToOne
    @Getter
    User creator = null;
    @Getter
    @Setter
    ApprovalStatus status = null;
    @ManyToOne
    TownHall townHall;

    public String getContentType() throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("Not implemented yet.");
    }

    public Content(String name, String description, Date creationDate, Long creator) {
        this.name = name;
        this.description = description;
        this.creationDate = creationDate;
        this.creator = new User();
        this.creator.setId(creator);
    }

    public Content(Long id) {
        this.setId(id);
    }
}
