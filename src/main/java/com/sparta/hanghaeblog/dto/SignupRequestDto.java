package com.sparta.hanghaeblog.dto;

import com.sparta.hanghaeblog.entity.User;
import com.sparta.hanghaeblog.entity.UserRoleEnum;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequestDto {

//    private final UserRoleEnum role;

    @NotBlank(message = "아이디는 필수 입력값입니다.")
    @Size(min=4, max=10)
    @Pattern(regexp = "^[a-z0-9]{4,10}", message = "이름은 알파벳 소문자와 숫자를 조합하여 4~10자까지 입력할 수 있습니다.")
    private String username;

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,16}$", message = "비밀번호는 8~16자리수여야 합니다. 영문 대소문자, 숫자, 특수문자를 1개 이상 포함해야 합니다.")
    private String password;

    private boolean admin = false;
    private String adminToken = "";
//
//    SignupRequestDto(User user){
//        this.username = user.getUsername();
//        this.password = user.getPassword();
//        this.role = user.getRole();
//    }
}
