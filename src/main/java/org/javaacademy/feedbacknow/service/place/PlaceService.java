package org.javaacademy.feedbacknow.service.place;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.javaacademy.feedbacknow.dto.CreatePlaceDto;
import org.javaacademy.feedbacknow.dto.PlaceDto;
import org.javaacademy.feedbacknow.dto.UpdatePlaceDto;
import org.javaacademy.feedbacknow.mapper.PlaceMapper;
import org.javaacademy.feedbacknow.repository.PlaceRepository;
import org.javaacademy.feedbacknow.service.integration.qr.QrGenerationService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PlaceService {
    private final QrGenerationService qrGenerationService;
    private final PlaceRepository placeRepository;
    private final PlaceMapper placeMapper;

    public UUID createPlace(CreatePlaceDto createPlaceDto) {
        PlaceDto placeDto = placeMapper.toDto(createPlaceDto);
        placeDto.setQrCode(qrGenerationService.getQrCode(placeDto.getName()));
        return placeRepository.save(placeMapper.toEntity(placeDto));
    }

    public byte[] findQrCodeByUuid(@NonNull UUID uuid) {
        return placeRepository.findQrCodeByUuid(uuid);
    }

    public PlaceDto findByName(@NonNull String name) {
        return placeMapper.toDto(placeRepository.findByName(name));
    }

//    public void updatePlace(UUID uuid, UpdatePlaceDto updatePlaceDto) {
//
//    }

}
