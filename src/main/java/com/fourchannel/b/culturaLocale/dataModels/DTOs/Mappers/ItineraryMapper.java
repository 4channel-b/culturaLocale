package com.fourchannel.b.culturaLocale.dataModels.DTOs.Mappers;

import com.fourchannel.b.culturaLocale.dataModels.Itinerary;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ItineraryMapper {
    ItineraryMapper INSTANCE = Mappers.getMapper(ItineraryMapper.class);
    Itinerary itineraryDtoToItinerary(Itinerary itineraryDTO);
}
