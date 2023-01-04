package com.sparta.hanghaeblog.service;

import com.sparta.hanghaeblog.entity.Comment;
import com.sparta.hanghaeblog.entity.CommentLike;
import com.sparta.hanghaeblog.entity.Post;
import com.sparta.hanghaeblog.entity.User;
import com.sparta.hanghaeblog.repository.CommentLikeRepository;
import com.sparta.hanghaeblog.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CommentLikeService {
        private final CommentLikeRepository commentLikeRepository;
        private final CommentRepository commentRepository;

//        public boolean addLike(User user, Long commentId) {
//            Comment comment = commentRepository.findById(commentId).orElseThrow();
//            CommentLike commentLike = new CommentLike(comment, user);
//            //중복 좋아요 방지
//            if(isNotAlreadyLike(comment, user)) {
//                commentLikeRepository.save(commentLike);
//            } else {
//                commentLikeRepository.delete(commentLike);
//            }
//            return true;
//            }
//        public boolean addLike(User user, Long commentId)

        //사용자가 이미 좋아요 한 게시물인지 체크
        private boolean isNotAlreadyLike(User user, Comment comment) {
            return commentLikeRepository.findByUserAndComment(user, comment).isEmpty();
        }
}
