package com.fourchannel.b.culturaLocale.dataModels;

import com.fourchannel.b.culturaLocale.dataModels.users.User;

import java.util.Date;

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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "content_id")
    private Long id;
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
