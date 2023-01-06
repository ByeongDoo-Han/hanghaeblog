package com.sparta.hanghaeblog.security;

import com.sparta.hanghaeblog.entity.User;
import com.sparta.hanghaeblog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        System.out.println("UserDetailsServiceImpl.loadUserByUsername : " + username);
        User loginUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));

        return new UserDetailsImpl(loginUser, loginUser.getUsername(), loginUser.getPassword());
    }

    @Transactional(readOnly = true)
    public Map<String, String> validateHandling(Errors errors){
        Map<String, String> errorMap = new HashMap<>();

        for(FieldError error : errors.getFieldErrors()){
            String validKeyName = String.format("valid_%s", error.getField());
            errorMap.put(validKeyName, error.getDefaultMessage());
        }
        return errorMap;
    }
}
