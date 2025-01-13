package org.javaacademy.feedbacknow.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Place {
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
