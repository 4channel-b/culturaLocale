package com.fourchannel.b.culturaLocale.dataModels.DTOs.Mappers;

import com.fourchannel.b.culturaLocale.dataModels.DTOs.PoiCreationRequestDTO;
import com.fourchannel.b.culturaLocale.dataModels.DTOs.UserCreationRequestDTO;
import com.fourchannel.b.culturaLocale.dataModels.PointOfInterest;
import com.fourchannel.b.culturaLocale.dataModels.users.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PoiMapper {
    PoiMapper INSTANCE = Mappers.getMapper(PoiMapper.class);
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "townhall", ignore = true)
    PointOfInterest poiDtoToPoi(PoiCreationRequestDTO poiDTO);
}
