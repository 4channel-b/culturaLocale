package com.fourchannel.b.culturaLocale.dataModels;

import com.fourchannel.b.culturaLocale.dataModels.users.User;

import java.util.Date;
<<<<<<< HEAD
=======

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
>>>>>>> d04bfd0c85866228d058fdb01f80b301d69f7962

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
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

    public abstract String getContentType(); // Needed to discern content types in class agnostic lists
}
