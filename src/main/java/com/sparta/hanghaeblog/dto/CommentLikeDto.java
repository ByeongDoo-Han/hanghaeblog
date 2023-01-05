package com.sparta.hanghaeblog.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentLikeDto {

    private String username;
    private Long commentId;

    public CommentLikeDto(String username, Long commentId){
        this.commentId = commentId;
        this.username = username;
    }
}
