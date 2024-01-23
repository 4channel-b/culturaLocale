package com.fourchannel.b.culturaLocale.dataModels.DTOs.Mappers;

import com.fourchannel.b.culturaLocale.dataModels.DTOs.EventCreationRequestDTO;
import com.fourchannel.b.culturaLocale.dataModels.Event;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EventMapper {
    EventMapper INSTANCE = Mappers.getMapper(EventMapper.class);
    @InheritConfiguration(name = "contentDtoToContent")
    Event eventDtoToEvent(EventCreationRequestDTO eventDTO);
}
