package com.sparta.hanghaeblog.repository;


import com.sparta.hanghaeblog.entity.Comment;
import com.sparta.hanghaeblog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByOrderByModifiedAtDesc();

    Optional<Comment> findAllByUsername(String username);
    Optional<Comment> findAllByUsernameAndCommentId(String username, Long commentId);

}
