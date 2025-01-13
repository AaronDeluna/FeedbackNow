package org.javaacademy.feedbacknow.repository;
import lombok.extern.slf4j.Slf4j;
import org.javaacademy.feedbacknow.entity.Feedback;
import org.javaacademy.feedbacknow.entity.Place;
import org.javaacademy.feedbacknow.exeption.NameIsExistException;
import org.javaacademy.feedbacknow.exeption.NameNotFoundException;
import org.javaacademy.feedbacknow.exeption.UuidIsExistException;
import org.javaacademy.feedbacknow.exeption.UuidNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Component
@Slf4j
public class PlaceRepository {
    private static final String UUID_EXIST_MESSAGE = "uuid: %s, is exist usage!";
    private static final String UUID_NOT_FOUND_MESSAGE = "uuid: %s, not found!";
    private static final String NAME_NOT_FOUND_MESSAGE = "name: %s, not found!";
    private static final String NAME_EXIST_MESSAGE = "name: %s, is exist";
    private final Map<UUID, Place> placeStorage = new HashMap<>();

    public UUID save(Place place) {
        validateName(place.getName());
        if (placeStorage.containsKey(place.getUuid())) {
            throw new UuidIsExistException(UUID_EXIST_MESSAGE.formatted(place.getUuid()));
        }
        placeStorage.put(place.getUuid(), place);
        return place.getUuid();
    }

    public byte[] findQrCodeByUuid(UUID uuid) {
        if (!placeStorage.containsKey(uuid)) {
            throw new UuidNotFoundException(UUID_NOT_FOUND_MESSAGE.formatted(uuid));
        }
        return placeStorage.get(uuid).getQrCode();
    }

    public Place findByName(String name) {
        return placeStorage.values().stream()
                .filter(place -> Objects.equals(place.getName(), name))
                .findFirst()
                .orElseThrow(
                        () -> new NameNotFoundException(NAME_NOT_FOUND_MESSAGE.formatted(name))
                );
    }

    public Place findByUuid(UUID uuid) {
        return placeStorage.get(uuid);
    }

    private void validateName(String name) {
        placeStorage.values().stream()
                .map(Place::getName)
                .filter(name::equals)
                .findAny()
                .ifPresent(n -> {
                    throw new NameIsExistException(NAME_EXIST_MESSAGE.formatted(name));
                });
    }

}
