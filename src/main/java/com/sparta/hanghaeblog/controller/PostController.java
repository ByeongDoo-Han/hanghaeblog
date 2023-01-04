package com.sparta.hanghaeblog.controller;

import com.sparta.hanghaeblog.dto.PostDeleteDto;
import com.sparta.hanghaeblog.dto.PostRequestDto;
import com.sparta.hanghaeblog.dto.PostResponseDto;
import com.sparta.hanghaeblog.entity.Post;
import com.sparta.hanghaeblog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth/posts")
public class PostController {

    private final PostService postService;

    @GetMapping("/")
    public ModelAndView home() {
        return new ModelAndView("index");
    }

    @PostMapping("")
    public PostResponseDto createPost(@RequestBody PostRequestDto requestDto){
        return postService.createPost(requestDto);
    }

    @GetMapping("/total")
    public List<PostResponseDto> getPosts() {
        return postService.getPosts();
    }

    @GetMapping("/{postId}")
    public PostResponseDto getOnePosts(@PathVariable Long postId){
        return postService.getOnePosts(postId);
    }

    @PutMapping("/{postId}")
    public PostResponseDto updatePosts(@PathVariable Long postId, @RequestBody PostRequestDto requestDto){
        return postService.updatePosts(postId, requestDto);
    }

    @PostMapping("/{postId}")
    public void deletePost(@PathVariable Long postId, @RequestBody PostDeleteDto postDeleteDto){
        postService.delete(postId, postDeleteDto);
    }
}
