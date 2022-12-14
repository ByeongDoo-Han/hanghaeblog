package com.sparta.hanghaeblog.repository;

import com.sparta.hanghaeblog.entity.Post;
import com.sparta.hanghaeblog.entity.PostLike;
import com.sparta.hanghaeblog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
//    Optional<PostLike> findByUserNameAndPostId(User username, Post postId);
    Optional<PostLike> findByUserAndPost(User user, Post post);
//    Optional<PostLike> findByUsernameAndPostId(String username, Long postId);

}
