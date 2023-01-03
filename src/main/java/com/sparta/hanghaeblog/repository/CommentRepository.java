package com.sparta.hanghaeblog.repository;


import com.sparta.hanghaeblog.entity.Comment;
import com.sparta.hanghaeblog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
