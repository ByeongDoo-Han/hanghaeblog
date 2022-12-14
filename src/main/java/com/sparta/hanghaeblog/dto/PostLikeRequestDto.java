package com.sparta.hanghaeblog.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostLikeRequestDto {


    private String username;
    private Long postId;

    public PostLikeRequestDto(String username, Long postId) {
        this.username = username;
        this.postId = postId;
    }
}

