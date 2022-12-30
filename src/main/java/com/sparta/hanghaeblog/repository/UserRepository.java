package com.sparta.hanghaeblog.repository;

import com.sparta.hanghaeblog.entity.Post;
import com.sparta.hanghaeblog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByUsername(String username);
    Optional<User> findByUsername(String username);
}
