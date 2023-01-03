package com.sparta.hanghaeblog.entity;

import com.sparta.hanghaeblog.dto.CommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Comment extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Post_Id", nullable = false)
    private Post post;



    public Comment(User user, CommentRequestDto requestDto, Post post) {
        this.username = user.getUsername();
        this.comment = requestDto.getComment();
        this.post = post;
    }

//    public void update(CommentRequestDto commentRequestDto) {
//        this.comments = commentRequestDto.getComments();
//    }

    public static boolean isSameNameComment(Comment comment, User user) {
        if (comment.getUsername().equals(user.getUsername())) {
            return true;
        } else {
            return false;
        }
    }
    public void update(CommentRequestDto commentRequestDto) {
        this.comment = commentRequestDto.getComment();
    }
}