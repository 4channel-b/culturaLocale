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
@Table(name="townhall")
public class TownHall {
    @Id
    private long Id;
    private String name;
    private String description;
    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location; // Assuming Location is a class you have defined elsewhere
    private double area;
    private Date establishmentDate;
}
