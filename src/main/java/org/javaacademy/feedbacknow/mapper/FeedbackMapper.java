package org.javaacademy.feedbacknow.mapper;

import org.javaacademy.feedbacknow.dto.CreateFeedbackDto;
import org.javaacademy.feedbacknow.dto.FeedbackDto;
import org.javaacademy.feedbacknow.entity.Feedback;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FeedbackMapper {

    public Feedback toEntity(CreateFeedbackDto dto) {
        return new Feedback(dto.getPlaceName(), dto.getPhoneNumber(), dto.getMessage(), dto.getStarsCount());
    }

    public FeedbackDto toDto(Feedback entity) {
        return FeedbackDto.builder()
                .uuid(entity.getUuid())
                .placeName(entity.getPlaceName())
                .phoneNumber(entity.getPhoneNumber())
                .message(entity.getMessage())
                .starsCount(entity.getStarsCount())
                .build();
    }

    public List<FeedbackDto> toDtos(List<Feedback> feedbacks) {
        return feedbacks.stream()
                .map(this::toDto)
                .toList();
    }
}
