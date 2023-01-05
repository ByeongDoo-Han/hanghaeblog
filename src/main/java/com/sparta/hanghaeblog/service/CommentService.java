package com.sparta.hanghaeblog.service;

import com.sparta.hanghaeblog.dto.CommentRequestDto;
import com.sparta.hanghaeblog.entity.Comment;
import com.sparta.hanghaeblog.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository CommentRepository;

    @Transactional
    public Comment createComment(CommentRequestDto requestDto, String username) {
        Comment comment = new Comment(requestDto,username);
        CommentRepository.save(comment);
        return comment;
    }
}