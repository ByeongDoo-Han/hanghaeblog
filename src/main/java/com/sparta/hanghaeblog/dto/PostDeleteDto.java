package com.sparta.hanghaeblog.dto;

import lombok.Getter;

@Getter
public class PostDeleteDto {
    private String username;
    private boolean admin = false;
    private String password;
}
