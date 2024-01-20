package com.fourchannel.b.culturaLocale.dataModels;

import com.fourchannel.b.culturaLocale.dataModels.users.User;

import java.util.Date;

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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="report_id_seq")
    private Long Id;
    @ManyToOne
    private User reporter;
    private String description;
    private Date reportingDate;
    private int status;
    private String resolution;
    @ManyToOne
    private Content content;
}