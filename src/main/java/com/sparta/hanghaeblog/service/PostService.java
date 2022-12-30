package com.sparta.hanghaeblog.service;

import com.sparta.hanghaeblog.dto.PostDeleteDto;
import com.sparta.hanghaeblog.dto.PostRequestDto;
import com.sparta.hanghaeblog.dto.PostResponseDto;
import com.sparta.hanghaeblog.entity.Post;
import com.sparta.hanghaeblog.jwt.JwtUtil;
import com.sparta.hanghaeblog.repository.PostRepository;
import com.sparta.hanghaeblog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.Jar;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    @Transactional
    public Post createPost(PostRequestDto requestDto) {
        Post post = new Post(requestDto);
        postRepository.save(post);
        return post;
    }

    @Transactional
    public List<Post> getPosts() {
        return postRepository.findAllByOrderByModifiedAtDesc();
    }

    @Transactional
    public PostResponseDto getOnePosts(Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시물이 존재하지 않습니다.")
        );
        return new PostResponseDto(post);
    }

    @Transactional
    public PostResponseDto updatePosts(Long id, PostRequestDto postRequestDto) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시물이 존재하지 않습니다.")
        );
        post.passwordValid(postRequestDto.getPassword());
        post.update(postRequestDto);
        return new PostResponseDto(post);
    }

    @Transactional
    public void delete(Long id, PostDeleteDto postDeleteDto) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시물이 존재하지 않습니다.")
        );
        post.passwordValid(postDeleteDto.getPassword());
        postRepository.delete(post);
        System.out.println("메세지 삭제에 성공했습니다.");
    }
}
