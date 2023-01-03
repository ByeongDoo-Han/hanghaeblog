package com.sparta.hanghaeblog.dto;

import com.sparta.hanghaeblog.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {

    private final Long id;
    private final String username;
    private final String comments;
//    private final int likes;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.username = comment.getUsername();
        this.comments = comment.getComment();
//        this.likes = comment.getLikes();
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
    }
}