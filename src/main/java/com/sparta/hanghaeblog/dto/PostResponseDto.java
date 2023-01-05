package com.sparta.hanghaeblog.dto;

import com.sparta.hanghaeblog.entity.Comment;
import com.sparta.hanghaeblog.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class PostResponseDto {
    private final Long postId;
    private final String title;
    private final String username;
    private final String contents;
    private final Integer likeCount;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;
    private List<CommentResponseDto> comments = new ArrayList<>();

    public PostResponseDto(Post post) {
        this.postId = post.getPostId();
        this.title = post.getTitle();
        this.username = post.getUsername();
        this.contents = post.getContents();
        this.likeCount = post.getPostLikes().size();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
        List<CommentResponseDto> list = new ArrayList<>();
        for (Comment comment : post.getComments()) {
            list.add(new CommentResponseDto(comment));
            this.comments = list;
        }
    }
}
