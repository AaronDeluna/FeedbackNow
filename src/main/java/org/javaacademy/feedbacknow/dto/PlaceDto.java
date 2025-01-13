package org.javaacademy.feedbacknow.dto;

import lombok.Builder;
import lombok.Data;
import org.javaacademy.feedbacknow.entity.CookingType;
import org.javaacademy.feedbacknow.entity.Feedback;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@Data
@Builder
public class PlaceDto {
    private UUID uuid;
    private String name;
    private CookingType cookingType;
    private String timeOpen;
    private String timeClose;
    private float rate;
    private String phone;
    private byte[] qrCode;
    private List<Feedback> feedbacks;
}
