package com.fourchannel.b.culturaLocale.dataModels.DTOs.Mappers;

import com.fourchannel.b.culturaLocale.dataModels.DTOs.UserBasicDTO;
import com.fourchannel.b.culturaLocale.dataModels.DTOs.UserCreationRequestDTO;
import com.fourchannel.b.culturaLocale.dataModels.DTOs.UserSuspensionDTO;
import com.fourchannel.b.culturaLocale.dataModels.users.User;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(builder = @Builder(disableBuilder = false))
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "Id", ignore = true) // Ignore the Id as it's auto-generated
    @Mapping(target = "preferredCategory", ignore = true) // Ignore as it's not in DTO
    @Mapping(target = "notificationList", ignore = true) // Ignore as it's not in DTO
    @Mapping(target = "suspended", ignore = true) // Ignore as it's not in DTO
    User userDtoToUser(UserCreationRequestDTO userDTO);
}