package com.fourchannel.b.culturaLocale.dataModels;

import lombok.*;

import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ImageSet {
    private List<Image> images;
}
