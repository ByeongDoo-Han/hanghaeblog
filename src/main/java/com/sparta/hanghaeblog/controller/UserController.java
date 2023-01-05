package com.sparta.hanghaeblog.controller;

import com.sparta.hanghaeblog.dto.LoginRequestDto;
import com.sparta.hanghaeblog.dto.SignupRequestDto;
import com.sparta.hanghaeblog.entity.User;
import com.sparta.hanghaeblog.entity.UserRoleEnum;
import com.sparta.hanghaeblog.repository.UserRepository;
import com.sparta.hanghaeblog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    //ADMIN_TOKEN
    private static final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";

    @PostMapping("/signup")
    public String signUp(@RequestBody SignupRequestDto signupRequestDto) {
        return userService.signUp(signupRequestDto);
    }

    @PostMapping("/login")
    public String logIn(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response){
        userService.logIn(loginRequestDto, response);
        return "로그인성공";
    }

    @GetMapping("/signup")
    public ModelAndView signup() {
        return new ModelAndView("signup");
    }

    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("login");
    }
}
