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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth/posts")
public class CommentLikeController {
    private final CommentLikeService commentLikeService;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @PostMapping("{commentId}/like/")
    public ResponseEntity<String> CommentLike(@PathVariable Long commentId, HttpServletRequest request) {
        boolean result = false;


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