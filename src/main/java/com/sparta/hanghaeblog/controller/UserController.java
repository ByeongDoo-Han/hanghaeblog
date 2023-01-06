package com.sparta.hanghaeblog.controller;

import com.sparta.hanghaeblog.dto.LoginRequestDto;
import com.sparta.hanghaeblog.dto.SignupRequestDto;
import com.sparta.hanghaeblog.entity.User;
import com.sparta.hanghaeblog.entity.UserRoleEnum;
import com.sparta.hanghaeblog.repository.UserRepository;
import com.sparta.hanghaeblog.security.UserDetailsServiceImpl;
import com.sparta.hanghaeblog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class UserController {

    private final UserService userService;
    private final UserDetailsServiceImpl userDetailsServiceImpl;

    @PostMapping("/signup")
    public String signUp(@RequestBody @Valid SignupRequestDto signupRequestDto, Errors errors, Model model) {

        if(errors.hasErrors()){
            model.addAttribute("signupDto", signupRequestDto);

            Map<String, String> errorMap = userDetailsServiceImpl.validateHandling(errors);
            for(String key : errorMap.keySet()) {
                model.addAttribute(key, errorMap.get(key));
            }

            return "/signup";
        }

        userService.signUp(signupRequestDto);
        return "회원가입성공";
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
