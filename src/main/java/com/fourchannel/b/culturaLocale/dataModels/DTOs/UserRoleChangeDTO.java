package com.fourchannel.b.culturaLocale.dataModels.DTOs;

import com.fourchannel.b.culturaLocale.dataModels.users.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRoleChangeDTO extends UserBasicDTO {
    Long townHallId;
    Role newRole;
}
