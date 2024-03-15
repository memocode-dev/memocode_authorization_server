package dev.memocode.memocode_authorization_server.out.outbox.entity;

import java.util.Optional;
import java.util.stream.Stream;

public enum EventType {
    USER_CREATED,
    ACCOUNT_AUTHORITY_UPDATED,
    ;

    public static Optional<EventType> fromString(String eventTypeString) {
        return Stream.of(EventType.values())
                .filter(e -> e.name().equals(eventTypeString))
                .findFirst();
    }
}
