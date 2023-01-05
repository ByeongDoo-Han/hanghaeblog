package com.sparta.hanghaeblog.service;

import com.sparta.hanghaeblog.dto.*;
import com.sparta.hanghaeblog.entity.Comment;
import com.sparta.hanghaeblog.entity.Post;
import com.sparta.hanghaeblog.jwt.JwtUtil;
import com.sparta.hanghaeblog.repository.PostRepository;
import com.sparta.hanghaeblog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.Jar;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    @Transactional
    public PostResponseDto createPost(PostRequestDto requestDto) {
        Post post = new Post(requestDto);
        postRepository.save(post);
        return new PostResponseDto(post);
    }

    @Transactional
    public List<PostResponseDto> getPosts() {
        List<Post> postList = postRepository.findAllByOrderByModifiedAtDesc();
        List<PostResponseDto> postsResponseDtoList = postList.stream().map(post -> new PostResponseDto(post)).collect(Collectors.toList());
        return postsResponseDtoList;
    }


    @Transactional
    public PostResponseDto getOnePosts(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("게시물이 존재하지 않습니다.")
        );
        return new PostResponseDto(post);
    }

    @Transactional
    public PostResponseDto updatePosts(Long postId, PostRequestDto postRequestDto) {
        if(postRequestDto.isAdmin()){
            Post post = postRepository.findById(postId).orElseThrow(
                    () -> new IllegalArgumentException("")
            );
            post.update(postRequestDto);
            return new PostResponseDto(post);
        } else {
            Post post = postRepository.findByPostIdAndUsername(postId, postRequestDto.getUsername()).orElseThrow(
                    () -> new IllegalArgumentException("수정 권한이 없습니다.")
            );
            post.passwordValid(postRequestDto.getPassword());
            post.update(postRequestDto);
            return new PostResponseDto(post);
        }
    }

    @Transactional
    public void delete(Long postId, PostDeleteDto postDeleteDto) {
        if(postDeleteDto.isAdmin()){
            Post post = postRepository.findById(postId).orElseThrow(
                    () -> new IllegalArgumentException("")
            );
            postRepository.delete(post);
            System.out.println("메세지 삭제에 성공했습니다.");
        } else {
            Post post = postRepository.findByPostIdAndUsername(postId, postDeleteDto.getUsername()).orElseThrow(
                    () -> new IllegalArgumentException("게시물이 존재하지 않습니다.")
            );
            post.passwordValid(postDeleteDto.getPassword());
            postRepository.delete(post);
            System.out.println("메세지 삭제에 성공했습니다.");
        }
    }
}
