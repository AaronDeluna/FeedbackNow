package org.javaacademy.feedbacknow.dto;

import lombok.Builder;
import lombok.Data;
import org.javaacademy.feedbacknow.entity.CookingType;

import java.util.UUID;

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
}
