package org.javaacademy.feedbacknow.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import org.javaacademy.feedbacknow.entity.CookingType;

@Data
@AllArgsConstructor
@NonNull
public class UpdatePlaceDto {
    private String name;
    @JsonProperty("cooking_type")
    private CookingType cookingType;
    @JsonProperty("time_open")
    private String timeOpen;
    @JsonProperty("time_close")
    private String timeClose;
    private String phone;
}
