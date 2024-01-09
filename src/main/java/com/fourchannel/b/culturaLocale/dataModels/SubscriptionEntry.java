package com.fourchannel.b.culturaLocale.dataModels;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class SubscriptionEntry
{
    //State declaration
    private Content content;
    private Contest contest;
}
