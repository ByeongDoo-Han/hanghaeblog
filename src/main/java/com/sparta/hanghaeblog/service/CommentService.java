package com.sparta.hanghaeblog.service;

import com.sparta.hanghaeblog.entity.Comment;
import com.sparta.hanghaeblog.entity.Post;
import com.sparta.hanghaeblog.entity.User;
import com.sparta.hanghaeblog.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    @Transactional
    public void deleteComment(Long id, Comment comment, User user, Post post) {
        if (User.isAdmin(user)){
            commentRepository.deleteById(id);
        } else if (Comment.isSameNameComment(comment, user)) {
            commentRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("권한이 없습니다.");
        }   // post 테이블에 있는 comment도 삭제해아하나?
    }
}
