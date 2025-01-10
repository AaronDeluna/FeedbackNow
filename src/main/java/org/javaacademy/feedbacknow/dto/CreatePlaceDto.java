package org.javaacademy.feedbacknow.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.javaacademy.feedbacknow.entity.CookingType;

@Data
@Builder
public class CreatePlaceDto {
    @NonNull
    private String name;
    @NonNull
    @JsonProperty("cooking_type")
    private CookingType cookingType;
    @NonNull
    @JsonProperty("time_open")
    private String timeOpen;
    @NonNull
    @JsonProperty("time_close")
    private String timeClose;
    @NonNull
    private String phone;
}
