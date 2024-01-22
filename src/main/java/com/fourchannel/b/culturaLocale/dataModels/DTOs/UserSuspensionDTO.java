package com.fourchannel.b.culturaLocale.dataModels.DTOs;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserSuspensionDTO extends UserBasicDTO {
    boolean newSuspensionStatus;
}
