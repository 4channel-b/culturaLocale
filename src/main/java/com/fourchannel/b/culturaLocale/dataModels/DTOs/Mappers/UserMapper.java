package com.fourchannel.b.culturaLocale.dataModels.DTOs.Mappers;

import com.fourchannel.b.culturaLocale.dataModels.DTOs.UserBasicDTO;
import com.fourchannel.b.culturaLocale.dataModels.DTOs.UserCreationRequestDTO;
import com.fourchannel.b.culturaLocale.dataModels.DTOs.UserSuspensionDTO;
import com.fourchannel.b.culturaLocale.dataModels.users.User;

public interface UserMapper {
    // TODO: Review this singleton
    UserMapper INSTANCE = new UserMapperImpl();

    User userDtoToUser(UserCreationRequestDTO userDTO);
}