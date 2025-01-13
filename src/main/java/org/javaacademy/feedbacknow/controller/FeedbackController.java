package org.javaacademy.feedbacknow.controller;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.javaacademy.feedbacknow.dto.CreateFeedbackDto;
import org.javaacademy.feedbacknow.service.feedback.FeedbackService;
import org.javaacademy.feedbacknow.service.integration.qr.QrGenerationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feedback")
@RequiredArgsConstructor
public class FeedbackController {
    private final FeedbackService feedbackService;

    @PostMapping
    public ResponseEntity<?> createFeedback(@RequestBody CreateFeedbackDto createFeedbackDto) {
        feedbackService.create(createFeedbackDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> getAllFeedbackByName(@PathVariable String name) {
        return ResponseEntity.ok().body(feedbackService.findAllByPlaceName(name));
    }

}
