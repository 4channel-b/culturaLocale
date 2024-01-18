package com.fourchannel.b.culturaLocale.dataModels;

import com.fourchannel.b.culturaLocale.dataModels.users.User;

import java.util.Date;

import com.fourchannel.b.culturaLocale.dataModels.users.User;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
@Table(name="report")
public class Report {
    @Id
    private Long ID;
    @ManyToOne
    private User reporter;
    private String description;
    private Date reportingDate;
    private int status;
    private String resolution;
    //dovrebbero essere content
    @ManyToOne
    private PointOfInterest content;
}