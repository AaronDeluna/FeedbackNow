package org.javaacademy.feedbacknow.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.javaacademy.feedbacknow.dto.CreateFeedbackDto;
import org.javaacademy.feedbacknow.dto.PlaceDto;
import org.javaacademy.feedbacknow.service.feedback.FeedbackService;
import org.javaacademy.feedbacknow.service.place.PlaceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Controller
@RequestMapping("/place")
@RequiredArgsConstructor
@Slf4j
public class PlaceViewController {
    private final PlaceService placeService;
    private final FeedbackService feedbackService;

    @GetMapping("/{uuid}")
    public String getPlacePage(@PathVariable UUID uuid, Model model) {
        PlaceDto placeDto = placeService.findByUuid(uuid);
        model.addAttribute("placeName", placeDto.getName());
        model.addAttribute("uuid", placeDto.getUuid());
        model.addAttribute("phone", placeDto.getPhone());
        model.addAttribute("feedbacks", placeDto.getFeedbacks());
        return "place";
    }

    @PostMapping("/{uuid}/feedback")
    public String submitFeedback(@PathVariable UUID uuid,
                                 @RequestParam String placeName,
                                 @RequestParam String phoneNumber,
                                 @RequestParam String message,
                                 @RequestParam int starsCount) {
        CreateFeedbackDto createFeedbackDto = CreateFeedbackDto.builder()
                .placeName(placeName)
                .phoneNumber(phoneNumber)
                .message(message)
                .starsCount(starsCount)
                .build();
        feedbackService.create(createFeedbackDto);
        return "redirect:/place/" + uuid;
    }
}
