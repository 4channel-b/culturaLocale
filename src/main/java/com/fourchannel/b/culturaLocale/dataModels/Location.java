package com.fourchannel.b.culturaLocale.dataModels;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
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
