package com.fourchannel.b.culturaLocale.dataModels;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Location
{
    private float longitude;
    private float latitude;
}
