package com.sparta.hanghaeblog.entity;

import lombok.Getter;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;

@Getter
public enum UserRoleEnum {
    ADMIN(Authority.ADMIN),
    USER(Authority.USER);

    private final String authority;
    UserRoleEnum(String authority) {
        this.authority = authority;
    }

    public static class Authority{
        public static final String USER = "ROLE_USER";
        public static final String ADMIN = "ROLE_ADMIN";
    }
}
