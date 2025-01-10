package org.javaacademy.feedbacknow.controller;

import lombok.RequiredArgsConstructor;
import org.javaacademy.feedbacknow.dto.CreatePlaceDto;
import org.javaacademy.feedbacknow.dto.PlaceDto;
import org.javaacademy.feedbacknow.dto.UpdatePlaceDto;
import org.javaacademy.feedbacknow.service.place.PlaceService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/place")
@RequiredArgsConstructor
public class PlaceController {
    private final PlaceService placeService;

    @PostMapping
    public ResponseEntity<?> createPlace(@RequestBody CreatePlaceDto createPlaceDto) {
        return ResponseEntity.ok().body(placeService.createPlace(createPlaceDto));
    }

    @GetMapping("/{name}")
    public ResponseEntity<PlaceDto> getPlaceDtoByName(@PathVariable String name) {
        return ResponseEntity.ok().body(placeService.findByName(name));
    }

    @GetMapping("/{uuid}/qr")
    public ResponseEntity<byte[]> getPlaceQrCodeByUuid(@PathVariable UUID uuid) {
        byte[] qrCodeImg = placeService.findQrCodeByUuid(uuid);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(qrCodeImg);
    }

//    @PutMapping("/{uuid}")
//    public ResponseEntity<?> updatePlaceByUuid(@PathVariable UUID uuid,
//                                               @RequestBody UpdatePlaceDto updatePlaceDto) {
//        placeService.updatePlace(uuid, updatePlaceDto);
//        return ResponseEntity.ok().build();
//    }
}
