package org.javaacademy.feedbacknow.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class FeedbackDto {
    private UUID uuid;
    private String placeName;
    private String phoneNumber;
    private String message;
    private int starsCount;
}
