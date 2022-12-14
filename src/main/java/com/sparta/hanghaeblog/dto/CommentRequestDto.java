package com.sparta.hanghaeblog.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentRequestDto {

    private String comment;
    private String username;
    private boolean admin = false;
    private String commentPassword;
}