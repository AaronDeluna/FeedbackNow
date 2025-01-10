package org.javaacademy.feedbacknow.mapper;

import org.javaacademy.feedbacknow.dto.CreatePlaceDto;
import org.javaacademy.feedbacknow.dto.PlaceDto;
import org.javaacademy.feedbacknow.entity.Place;
import org.springframework.stereotype.Component;

@Component
public class PlaceMapper {

    public PlaceDto toDto(CreatePlaceDto createPlaceDto) {
        return PlaceDto.builder()
                .name(createPlaceDto.getName())
                .cookingType(createPlaceDto.getCookingType())
                .timeOpen(createPlaceDto.getTimeOpen())
                .timeClose(createPlaceDto.getTimeClose())
                .phone(createPlaceDto.getPhone())
                .build();
    }

    public PlaceDto toDto(Place entity) {
        return PlaceDto.builder()
                .uuid(entity.getUuid())
                .name(entity.getName())
                .cookingType(entity.getCookingType())
                .timeOpen(entity.getTimeOpen())
                .timeClose(entity.getTimeClose())
                .phone(entity.getPhone())
                .qrCode(entity.getQrCode())
                .build();
    }

    public Place toEntity(PlaceDto dto) {
        return Place.builder()
                .name(dto.getName())
                .cookingType(dto.getCookingType())
                .timeOpen(dto.getTimeOpen())
                .timeClose(dto.getTimeClose())
                .rate(dto.getRate())
                .phone(dto.getPhone())
                .qrCode(dto.getQrCode())
                .build();
    }
}
