package com.fourchannel.b.culturaLocale.dataModels.DTOs.Mappers;

import com.fourchannel.b.culturaLocale.dataModels.Content;
import com.fourchannel.b.culturaLocale.dataModels.DTOs.ContentCreationRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
@Mapper
public interface ContentMapper {
    ContentMapper INSTANCE = Mappers.getMapper(ContentMapper.class);
    @Mapping(target = "creator", ignore = true)
    Content contentDtoToContent(ContentCreationRequestDTO contentDTO);
}
