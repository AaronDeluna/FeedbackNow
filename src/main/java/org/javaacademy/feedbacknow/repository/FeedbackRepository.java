package org.javaacademy.feedbacknow.repository;

import org.javaacademy.feedbacknow.entity.Feedback;
import org.javaacademy.feedbacknow.exeption.PhoneNumberExistException;
import org.javaacademy.feedbacknow.exeption.UuidIsExistException;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Component
public class FeedbackRepository {
    private final Map<UUID, Feedback> feedbackStorage = new HashMap<>();

    public UUID save(Feedback feedback) {
        feedback.setUuid(UUID.randomUUID());
        if (feedbackStorage.containsKey(feedback.getUuid())) {
            throw new UuidIsExistException();
        }
        feedbackStorage.put(feedback.getUuid(), feedback);
        return feedback.getUuid();
    }

    public List<Feedback> findAllByPlaceName(String placeName) {
        return feedbackStorage.values().stream()
                .filter(feedback -> Objects.equals(feedback.getPlaceName(), placeName))
                .toList();
    }
}
