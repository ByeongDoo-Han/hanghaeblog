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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String comment;

    @Column(nullable = false)
    private String commentPassword;

    @ManyToOne(
            fetch = FetchType.LAZY

    )
    @JoinColumn(
            name = "Post_Id",
            nullable = false
    )
    private Post post;


    //Dto 넣으면 안된다고 함
    public Comment(User user, CommentRequestDto requestDto, Post post) {
        this.username = user.getUsername();
        this.comment = requestDto.getComment();
        this.commentPassword = requestDto.getCommentPassword();
        this.post = post;
    }

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

    public void passwordValid(String commentPassword) {
        if(!commentPassword.equals(this.getCommentPassword())){
            throw new IllegalArgumentException("비밀번호 불일치");
        }
    }
}