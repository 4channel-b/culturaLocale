package com.fourchannel.b.culturaLocale.dataModels;

import lombok.*;

import java.util.Date;

//Generic Townhall
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class TownHall {
    private String ID;
    private String name;
    private String description;
    private Location location; // Assuming Location is a class you have defined elsewhere
    private double area;
    private Date establishmentDate;
}
