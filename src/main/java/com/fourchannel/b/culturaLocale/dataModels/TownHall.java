package com.fourchannel.b.culturaLocale.dataModels;

import com.fourchannel.b.culturaLocale.dataModels.DTOs.TownHallCreationRequestDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name="townhall")
public class TownHall {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="townHall_id_seq")
    private Long Id;
    private String name;
    private String description;
    @Embedded
    private Location location;
    private double area;
    private Date establishmentDate;

    public TownHall(TownHallCreationRequestDTO dto) {
        this.name = dto.getName();
        this.description = dto.getDescription();
        this.location = dto.getLocation();
        this.area = dto.getArea();
        this.establishmentDate = dto.getEstablishmentDate();
    }
}
