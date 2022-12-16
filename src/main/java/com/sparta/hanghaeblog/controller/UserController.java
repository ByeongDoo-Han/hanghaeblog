package com.sparta.hanghaeblog.controller;

import com.sparta.hanghaeblog.dto.UserRequest;
import com.sparta.hanghaeblog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/api/auth/signup")
    public ResponseEntity<String> signUp(final @RequestBody @Valid UserRequest userRequest) {
        userService.signUp(userRequest);
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @PostMapping("/api/auth/login")
    public void logIn(@RequestBody @Valid UserRequest userRequest){
        userService.logIn(userRequest);
    }

    @GetMapping("/api/auth/signup")
    public ModelAndView signup() {
        return new ModelAndView("signup");
    }

    @GetMapping("/api/auth/login")
    public ModelAndView login() {
        return new ModelAndView("login");
    }
}
