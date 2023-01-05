package com.sparta.hanghaeblog.dto;

import com.sparta.hanghaeblog.entity.UserRoleEnum;
import lombok.Getter;

@Getter
public class SignupRequestDto {

    private String username;
    private String password;
    private boolean admin = false;
    private String adminToken = "";
}
