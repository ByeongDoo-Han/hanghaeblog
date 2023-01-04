package com.sparta.hanghaeblog.entity;


import com.sparta.hanghaeblog.dto.PostRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Post extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long postId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private String password;

    @OrderBy(value = "modifiedAt desc")
    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.REMOVE
    )
    private List<Comment> comments = new ArrayList<>();

    public Post(PostRequestDto requestDto){
        this.title = requestDto.getTitle();
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
        this.password = requestDto.getPassword();
    }

    public void update(PostRequestDto postRequestDto) {
        this.title = postRequestDto.getTitle();
        this.username = postRequestDto.getUsername();
        this.contents = postRequestDto.getContents();
        this.password = postRequestDto.getPassword();
    }

    public void passwordValid(String password) {
        if(!password.equals(this.getPassword())){
            throw new IllegalArgumentException("비밀번호 불일치");
        };
    }
    public void addCommentList(Comment comment){
        this.comments.add(comment);
    }
}
