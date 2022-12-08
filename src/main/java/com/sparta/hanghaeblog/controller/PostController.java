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
public class PostController {

    private final PostService postService;

    @GetMapping("/")
    public ModelAndView home() {
        return new ModelAndView("index");
    }

    @PostMapping("/api/posts")
    public Post createPost(@RequestBody PostRequestDto requestDto){
        return postService.createPost(requestDto);
    }

    @GetMapping("/api/posts")
    public List<Post> getPosts() {
        return postService.getPosts();
    }

    @GetMapping("/api/posts/{id}")
    public PostResponseDto getOnePosts(@PathVariable Long id){
        return postService.getOnePosts(id);
    }

    @PutMapping("/api/posts/{id}")
    public PostResponseDto updatePosts(@PathVariable Long id, @RequestBody PostRequestDto requestDto){
        return postService.updatePosts(id, requestDto);
    }

    @PostMapping("/api/posts/{id}")
    public void deletePost(@PathVariable Long id, @RequestBody PostDeleteDto postDeleteDto){
        postService.delete(id, postDeleteDto);
    }


}
