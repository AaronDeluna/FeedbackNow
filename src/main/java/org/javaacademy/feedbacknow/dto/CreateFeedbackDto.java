package org.javaacademy.feedbacknow.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateFeedbackDto {
    private String placeName;
    private String phoneNumber;
    private String message;
    private int starsCount;
}
