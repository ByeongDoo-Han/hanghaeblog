package com.sparta.hanghaeblog.repository;

import com.sparta.hanghaeblog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

public interface PostRepository extends JpaRepository<Post,Long>{
    List<Post> findAllByOrderByModifiedAtDesc();
    Optional<Post> findAllByUsername(String username);
    Optional<Post> findByIdAndUsername(Long id, String username);

}
