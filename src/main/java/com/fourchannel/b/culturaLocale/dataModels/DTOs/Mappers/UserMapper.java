package com.fourchannel.b.culturaLocale.mapper;

import com.fourchannel.b.culturaLocale.dataModels.DTOs.UserBasicDTO;
import com.fourchannel.b.culturaLocale.dataModels.DTOs.UserCreationRequestDTO;
import com.fourchannel.b.culturaLocale.dataModels.DTOs.UserSuspensionDTO;
import com.fourchannel.b.culturaLocale.dataModels.users.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    // Existing mapping
    @Mapping(target = "id", ignore = true) // Ignore id since it's auto-generated
    @Mapping(target = "preferredCategory", ignore = true) // Ignore preferredCategory, not present in DTO
    @Mapping(target = "notificationList", ignore = true) // Ignore notificationList, not present in DTO
    User userDtoToUser(UserCreationRequestDTO userDTO);
}