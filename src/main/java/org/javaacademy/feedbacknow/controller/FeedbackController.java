package org.javaacademy.feedbacknow.controller;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.javaacademy.feedbacknow.service.integration.qr.QrGenerationService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feed-back")
@RequiredArgsConstructor
public class FeedbackController {
    private final QrGenerationService qrGenerationService;

    @GetMapping
    @SneakyThrows
    public ResponseEntity<?> generateQr(@RequestParam String header) {
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(qrGenerationService.getQrCode(header));
    }

}
