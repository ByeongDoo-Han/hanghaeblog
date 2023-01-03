package com.sparta.hanghaeblog.controller;

import com.sparta.hanghaeblog.dto.CommentRequestDto;
import com.sparta.hanghaeblog.dto.CommentResponseDto;
import com.sparta.hanghaeblog.entity.Comment;
import com.sparta.hanghaeblog.entity.Post;
import com.sparta.hanghaeblog.entity.User;
import com.sparta.hanghaeblog.jwt.JwtUtil;
import com.sparta.hanghaeblog.repository.CommentRepository;
import com.sparta.hanghaeblog.repository.PostRepository;
import com.sparta.hanghaeblog.repository.UserRepository;
import com.sparta.hanghaeblog.service.CommentService;
import io.jsonwebtoken.Claims;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class CommentContorller {
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final CommentService commentService;

    @PutMapping("/api/posts/{postId}/comments/{commentId}")
    public CommentResponseDto updateComments(@PathVariable Long postId, @PathVariable Long commentId, @RequestBody CommentRequestDto requestDto, HttpServletRequest request) {
        // Request에서 Token 가져오기
        String token = jwtUtil.resolveToken(request); // 토큰 꺼내기
        Claims claims;

        if (token != null) {
            // Token 검증
            if (jwtUtil.validateToken(token)) {
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("토큰이 존재하지 않습니다.");
            }
            // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );

            Post post = postRepository.findById(postId).orElseThrow(
                    () -> new IllegalArgumentException("게시글이 존재하지 않습니다."));

        }
        return commentService.updateComments(commentId, requestDto);
    }

    @PostMapping("/api/posts/{postId}/comments")
    public CommentResponseDto createComment(@PathVariable Long postId, @RequestBody CommentRequestDto commentRequestDto, HttpServletRequest request) {
        // Request에서 Token 가져오기
        String token = jwtUtil.resolveToken(request); // 토큰 꺼내기
        Claims claims;

        if (token != null) {
            // Token 검증
            if (jwtUtil.validateToken(token)) {
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("토큰이 존재하지 않습니다.");
            }
            // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );

            Post post = postRepository.findById(postId).orElseThrow(
                    () -> new IllegalArgumentException("게시글이 존재하지 않습니다."));
            return commentService.createComment(user, post, commentRequestDto);
        } else {
            throw new IllegalArgumentException("토큰이 없습니다.");
        }
    }

    @DeleteMapping("/api/posts/{post_id}/comments/{comment_id}")
    public Map deleteComment(@PathVariable Long post_id, @PathVariable Long comment_id, HttpServletRequest request) {
        // Request에서 Token 가져오기
        String token = jwtUtil.resolveToken(request); // 토큰 꺼내기
        Claims claims;

        if (token != null) {
            // Token 검증
            if (jwtUtil.validateToken(token)) {
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("토큰이 존재하지 않습니다.");
            }
            // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );

            Post post = postRepository.findById(post_id).orElseThrow(
                    () -> new IllegalArgumentException("게시글이 존재하지 않습니다."));

            Comment comment = commentRepository.findById(comment_id).orElseThrow(
                    () -> new IllegalArgumentException("댓글이 존재하지 않습니다.")
            );
            commentService.deleteComment(comment_id, comment, user, post);
            Map map = new HashMap<>();
            map.put("msg", "게시물 삭제 성공");
            map.put("statusCode", "200");
            return map;
        } else {
            throw new IllegalArgumentException("토큰이 없습니다.");
        }
    }
}
