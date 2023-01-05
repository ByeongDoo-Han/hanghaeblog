package com.sparta.hanghaeblog.dto;

import com.sparta.hanghaeblog.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class CommentResponseDto {

    private final Long commentId;
    private final String username;
    private final String comments;
    private final Integer likeCount;

    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;


    public CommentResponseDto(Comment comment) {
        this.commentId = comment.getCommentId();
        this.username = comment.getUsername();
        this.comments = comment.getComment();
//        this.likes = comment.getLikes();
        this.likeCount = comment.getCommentLikes().size();
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
    }
}