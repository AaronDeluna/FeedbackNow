package org.javaacademy.feedbacknow.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

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
}
