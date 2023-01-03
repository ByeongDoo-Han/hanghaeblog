package com.sparta.hanghaeblog.controller;

import com.sparta.hanghaeblog.dto.CommentRequestDto;
import com.sparta.hanghaeblog.entity.Comment;
import com.sparta.hanghaeblog.entity.Post;
import com.sparta.hanghaeblog.entity.User;
import com.sparta.hanghaeblog.jwt.JwtUtil;
import com.sparta.hanghaeblog.repository.UserRepository;
import com.sparta.hanghaeblog.service.CommentLikeService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@RequiredArgsConstructor
@RestController
public class CommentLikeController {
    private final CommentLikeService commentLikeService;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @PostMapping("/like/{commentId}")
    public ResponseEntity<String> CommentLike(@PathVariable Long commentId, HttpServletRequest request) {
        boolean result = false;
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
            if (user.getUsername().equals(userRepository.findByUsername(user.getUsername()))) {
                result = commentLikeService.addLike(user, commentId);
            }

        }
        return result ?
                new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}

//    @PostMapping("/like/{commentId}")
//    public ResponseEntity<String> addLike(
//            @AuthenticationPrincipal UserAdapter UserAdapter,
//            @PathVariable Long commentId) {
//
//        boolean result = false;
//
//        if (memberAdapter != null) {
//            result = CommentLikeService.addLike(memberAdapter.getMember(), recipeId);
//        }
//
//        return result ?
//                new ResponseEntity<>(HttpStatus.OK)
//                : new ResponseEntity<>(HttpStatterus.BAD_REQUEST);
//    }
//}