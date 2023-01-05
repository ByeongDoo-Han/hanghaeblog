package com.sparta.hanghaeblog.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostRequestDto {
    private String title;
    private String username;
    private String contents;
    private String password;
    private boolean admin = false;
    private LocalDateTime createdAt;
}