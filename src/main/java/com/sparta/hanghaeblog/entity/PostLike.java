package com.sparta.hanghaeblog.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Getter
@NoArgsConstructor
@Entity
public class PostLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "postLikeId")
    private Long postLikeId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "username")
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "postId")
    private Post post;

    // 연관 관계 편의 메소드
    public void setUser(User user){
        this.user = user;
    }
    public void setPost(Post post){
        this.post = post;
    }
}

