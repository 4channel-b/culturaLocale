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
public class Content implements IContent {
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

    @Override
    public String getContentType() throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("Not implemented yet.");
    }
}
