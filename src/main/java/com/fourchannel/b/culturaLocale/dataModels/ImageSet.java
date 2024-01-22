package com.fourchannel.b.culturaLocale.dataModels;

import com.fourchannel.b.culturaLocale.databaseModels.Image;
import lombok.*;

import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ImageSet {
    private List<Image> images;
}
