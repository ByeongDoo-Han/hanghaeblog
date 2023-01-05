package com.sparta.hanghaeblog.service;

import com.sparta.hanghaeblog.dto.PostLikeRequestDto;
import com.sparta.hanghaeblog.dto.PostResponseDto;
import com.sparta.hanghaeblog.entity.Post;
import com.sparta.hanghaeblog.entity.PostLike;
import com.sparta.hanghaeblog.entity.User;
import com.sparta.hanghaeblog.repository.PostLikeRepository;
import com.sparta.hanghaeblog.repository.PostRepository;
import com.sparta.hanghaeblog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class PostLikeService {

    private final PostLikeRepository postLikeRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public boolean addLike(User user, Long postId){
        Post post = postRepository.findById(postId).orElseThrow();

        //좋아요 중복 방지
        if(isNotAlreadyLike(user, post)) {
            PostLike postLike = new PostLike();
            postLike.setUser(user);
            postLike.setPost(post);
            postLikeRepository.save(postLike);
            return true;
        }
        return false;
    }

    private boolean isNotAlreadyLike(User user, Post post) {
        return postLikeRepository.findByUserAndPost(user, post).isEmpty();
    }
}
