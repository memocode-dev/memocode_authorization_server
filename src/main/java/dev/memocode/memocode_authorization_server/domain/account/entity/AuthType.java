package dev.memocode.memocode_authorization_server.domain.account.entity;

import lombok.Getter;

import java.util.Optional;

@Getter
public enum AuthType {
    KAKAO,
    EMAIL,
    ;

    public static Optional<AuthType> fromString(String type) {
        try {
            return Optional.of(AuthType.valueOf(type.toUpperCase()));
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }
}
