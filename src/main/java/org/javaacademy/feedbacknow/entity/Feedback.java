package org.javaacademy.feedbacknow.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Feedback {
    private UUID uuid;
    private String placeName;
    private String phoneNumber;
    private String message;
    private int starsCount;

    public Feedback(String placeName, String phoneNumber, String message, int starsCount) {
        this.placeName = placeName;
        this.phoneNumber = phoneNumber;
        this.message = message;
        this.starsCount = starsCount;
    }
}
