package com.sparta.hanghaeblog.entity;

import com.sparta.hanghaeblog.dto.SignupRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@NoArgsConstructor
@Entity(name = "users")
public class User {

    @Id
//    @GeneratedValue(strategy = IDENTITY)
    @Column(nullable = false, unique = true)
//    @Size(min = 4, max = 10)
//    @Pattern(regexp = "^[a-z0-9]+$")
    private String username;

    @Column(nullable = false)
//    @Size(min =8, max=15)
//    @Pattern(regexp = "^[A-Za-z0-9]+$")
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRoleEnum role;

    public User(String username, String password, UserRoleEnum role){
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public static boolean isAdmin(User user) {
        if (user.getRole() == UserRoleEnum.ADMIN){
            return true;
        } else {
            return false;
        }
    }
}
