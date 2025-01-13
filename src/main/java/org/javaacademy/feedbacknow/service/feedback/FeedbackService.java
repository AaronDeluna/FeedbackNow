package org.javaacademy.feedbacknow.service.feedback;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.javaacademy.feedbacknow.dto.CreateFeedbackDto;
import org.javaacademy.feedbacknow.dto.FeedbackDto;
import org.javaacademy.feedbacknow.entity.Feedback;
import org.javaacademy.feedbacknow.exeption.PhoneNumberExistException;
import org.javaacademy.feedbacknow.mapper.FeedbackMapper;
import org.javaacademy.feedbacknow.repository.FeedbackRepository;
import org.javaacademy.feedbacknow.repository.PlaceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FeedbackService {
    private final FeedbackRepository feedbackRepository;
    private final FeedbackMapper feedbackMapper;
    private final PlaceRepository placeRepository;

    public UUID create(CreateFeedbackDto createFeedbackDto) {
        Feedback feedback = feedbackMapper.toEntity(createFeedbackDto);
        UUID uuid = feedbackRepository.save(feedback);
        placeRepository.findByName(createFeedbackDto.getPlaceName()).getFeedbacks().add(feedback);
        return uuid;
    }

    public List<FeedbackDto> findAllByPlaceName(@NonNull String placeName) {
        return feedbackMapper.toDtos(feedbackRepository.findAllByPlaceName(placeName));
    }
}
