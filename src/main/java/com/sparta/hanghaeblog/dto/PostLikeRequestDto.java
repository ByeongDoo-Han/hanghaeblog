package com.sparta.hanghaeblog.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostLikeRequestDto {


    private Long username;
    private Long postId;

    public PostLikeRequestDto(Long username, Long postId) {
        this.username = username;
        this.postId = postId;
    }
}

