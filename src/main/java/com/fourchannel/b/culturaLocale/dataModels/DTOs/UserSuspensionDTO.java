package com.fourchannel.b.culturaLocale.dataModels.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserSuspensionDTO extends UserBasicDTO {
    boolean newSuspensionStatus;
}
