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

    User userDtoToUser(UserCreationRequestDTO userDTO);
}