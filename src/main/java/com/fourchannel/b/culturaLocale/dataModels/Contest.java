package com.fourchannel.b.culturaLocale.dataModels;


import lombok.*;

import java.util.Date;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Contest {
    private Long Id;
    private String name;
    private String description;
    private Date initialDate;
    private Date endDate;
    private String rules;
    private String type;
    private List<Content> contents;

    public String getType() {
        return type;
    }
}
