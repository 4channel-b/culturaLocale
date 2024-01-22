package com.fourchannel.b.culturaLocale.dataModels;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name="location")
public class Location
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="location_id_seq")
    private Long Id;
    private float longitude;
    private float latitude;
}
