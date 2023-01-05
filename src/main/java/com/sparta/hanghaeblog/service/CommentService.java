package com.sparta.hanghaeblog.service;

import com.sparta.hanghaeblog.dto.CommentDeleteDto;
import com.sparta.hanghaeblog.dto.CommentRequestDto;
import com.sparta.hanghaeblog.dto.CommentResponseDto;
import com.sparta.hanghaeblog.dto.PostDeleteDto;
import com.sparta.hanghaeblog.entity.Comment;
import com.sparta.hanghaeblog.entity.Post;
import com.sparta.hanghaeblog.entity.User;
import com.sparta.hanghaeblog.repository.CommentRepository;
import com.sparta.hanghaeblog.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Transactional
    public CommentResponseDto createComment(User user, Post post, CommentRequestDto requestDto) {
        Comment comment = new Comment(user, requestDto, post);
        commentRepository.save(comment);
        post.addCommentList(comment);
        return new CommentResponseDto(comment);
    }


    @Transactional
    public CommentResponseDto updateComments(Long commentId, CommentRequestDto commentRequestDto) {
        if(commentRequestDto.isAdmin()){
            Comment comment = commentRepository.findById(commentId).orElseThrow(
                    () -> new IllegalArgumentException("ADMIN 토큰이 일치하지 않습니다.")
            );
            comment.update(commentRequestDto);
            return new CommentResponseDto(comment);
        } else {
            Comment comment = commentRepository.findByUsernameAndCommentId(commentRequestDto.getUsername(), commentId).orElseThrow(
                    () -> new IllegalArgumentException("수정 권한이 없습니다.")
            );
            comment.passwordValid(commentRequestDto.getCommentPassword());
            comment.update(commentRequestDto);
            return new CommentResponseDto(comment);
        }
    }



    @Transactional
    public void delete(Long postId, Long commentId, CommentDeleteDto commentDeleteDto) {
        if(commentDeleteDto.isAdmin()){
            Comment comment = commentRepository.findById(commentId).orElseThrow(
                    () -> new IllegalArgumentException("댓글이 존재하지 않습니다.")
            );
            comment.passwordValid(commentDeleteDto.getCommentPassword());
            commentRepository.delete(comment);
            // Retrieve the post with the given ID
            Post post = postRepository.findById(postId).orElseThrow();
            // Find the comment with the given ID in the list of comments
            for (Iterator<Comment> iterator = post.getComments().iterator(); iterator.hasNext(); ) {
                Comment comment1 = iterator.next();
                if (comment1.getCommentId().equals(commentId)) {
                    // Remove the comment from the list
                    iterator.remove();
                    break;
                }
            }
            // Save the updated post back to the database
            postRepository.save(post);
            System.out.println("메세지 삭제에 성공했습니다.");
        } else {
            Comment comment = commentRepository.findByUsernameAndCommentId(commentDeleteDto.getUsername(), commentId).orElseThrow(
                    () -> new IllegalArgumentException("댓글이 존재하지 않습니다.")
            );
            comment.passwordValid(commentDeleteDto.getCommentPassword());
            commentRepository.delete(comment);
            // Retrieve the post with the given ID
            Post post = postRepository.findById(postId).orElseThrow();
            // Find the comment with the given ID in the list of comments
            for (Iterator<Comment> iterator = post.getComments().iterator(); iterator.hasNext(); ) {
                Comment comment1 = iterator.next();
                if (comment1.getCommentId().equals(commentId)) {
                    // Remove the comment from the list
                    iterator.remove();
                    break;
                }
            }
            // Save the updated post back to the database
            postRepository.save(post);
            System.out.println("메세지 삭제에 성공했습니다.");
        }
    }
}
