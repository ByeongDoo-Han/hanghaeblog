package com.sparta.hanghaeblog.service;

import com.sparta.hanghaeblog.entity.*;
import com.sparta.hanghaeblog.repository.CommentLikeRepository;
import com.sparta.hanghaeblog.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CommentLikeService {
        private final CommentLikeRepository commentLikeRepository;
        private final CommentRepository commentRepository;

        public boolean addLike(User user, Long postId, Long commentId){
                Comment comment = commentRepository.findById(commentId).orElseThrow();

                //좋아요 중복 방지
                if(isNotAlreadyLike(user, comment)) {
                        CommentLike commentLike = new CommentLike();
                        commentLike.setUser(user);
                        commentLike.setComment(comment);
                        commentLikeRepository.save(commentLike);
                        return true;
                } else{
                        CommentLike like = commentLikeRepository.findByUserAndComment(user, comment).orElseThrow();
                        commentLikeRepository.delete(like);
                }
                return false;
        }

        //사용자가 이미 좋아요 한 게시물인지 체크
        private boolean isNotAlreadyLike(User user, Comment comment) {
            return commentLikeRepository.findByUserAndComment(user, comment).isEmpty();
        }

}
