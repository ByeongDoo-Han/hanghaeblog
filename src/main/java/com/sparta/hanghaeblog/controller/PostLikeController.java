package com.sparta.hanghaeblog.controller;

import com.sparta.hanghaeblog.dto.PostRequestDto;
import com.sparta.hanghaeblog.repository.PostLikeRepository;
import com.sparta.hanghaeblog.repository.PostRepository;
import com.sparta.hanghaeblog.repository.UserRepository;
import com.sparta.hanghaeblog.security.UserDetailsImpl;
import com.sparta.hanghaeblog.service.PostLikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth/posts")
public class PostLikeController {
    private final PostLikeService postLikeService;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final PostLikeRepository postLikeRepository;

    @PostMapping("/{postId}/like")
    public ResponseEntity<String> addLike(
            @AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
            @PathVariable Long postId){
        boolean result = false;

        if(userDetailsImpl != null){
            result = postLikeService.addLike(userDetailsImpl.getUser(), postId);
        }

        return result ? new ResponseEntity<>(HttpStatus.OK):new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

//    @PostMapping(value = "{postId}/like")
//    public ResponseBody insert(@PathVariable Long postId, @RequestBody PostLikeRequestDto postLikeRequestDto) {
//        User user = userRepository.findById(postLikeRequestDto.getUsername())
//                .orElseThrow(() -> new IllegalArgumentException("Could not found username"));
//
//        Post postId = postRepository.findById(postLikeRequestDto.getPostId())
//                .orElseThrow(() -> new IllegalArgumentException("Could not found postId"));
//        postLikeService.insertPostLike(postLikeRequestDto);
//        return (ResponseBody) postLikeRequestDto;
//    }

//    @PostMapping("{postId}/like")
//    public PostRequestDto addPostLike(@PathVariable Long postId,  @AuthenticationPrincipal UserDetailsImpl userDetailsImpl){
//        return postLikeService.insertPostLike(postId, userDetailsImpl.getUsername());
//    }

//    @DeleteMapping(value = "{postId}/dislike")
//    public ResponseBody delete(@PathVariable Long postId, @RequestBody PostLikeRequestDto postLikeRequestDto) {
//        postLikeService.deletePostLike(postLikeRequestDto);
//        return (ResponseBody) postLikeRequestDto;
//    }
//    @PostMapping("{postId}/{username}/like")
//    public String Like(@PathVariable Long postId, @PathVariable String username){
//        if (postRepository.findById(postId).isEmpty()){
//            throw new IllegalArgumentException("게시물이 존재하지 않습니다.");
//        }
//        if (userRepository.findByUsername(username).isEmpty()){
//            throw new IllegalArgumentException("유저가 존재하지 않습니다.");
//        }
//        if (postLikeRepository.findByUsernameAndPostId(username, postId).isEmpty()){
//            postLikeService.postLikeYes();
//            String hi = "좋아요~";
//            return hi;
//        } else {
//            postLikeService.postLikeNo();
//            String hi = "싫어요~";
//            return hi;
//        }
}
