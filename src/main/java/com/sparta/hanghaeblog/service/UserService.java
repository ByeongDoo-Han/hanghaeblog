package com.sparta.hanghaeblog.service;

import com.sparta.hanghaeblog.dto.UserRequest;
import com.sparta.hanghaeblog.entity.User;
import com.sparta.hanghaeblog.jwt.JwtUtil;
import com.sparta.hanghaeblog.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class UserService {

    @Transactional(readOnly = true)
    public void logIn(UserRequest userRequest){
        String username = userRequest.getUsername();
        String password = userRequest.getPassword();

        User user = (User) UserRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("등록된 사용자가 없습니다.")
        );

        // 비밀번호 확인
        if(!user.getPassword().equals(password)){
            throw  new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }

    @Transactional(readOnly = true)
    public void signUp(UserRequest userRequest){

    }
}
