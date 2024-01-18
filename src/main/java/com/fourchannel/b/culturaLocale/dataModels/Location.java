package com.fourchannel.b.culturaLocale.dataModels;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    private Long id;
    private float longitude;
    private float latitude;
}
