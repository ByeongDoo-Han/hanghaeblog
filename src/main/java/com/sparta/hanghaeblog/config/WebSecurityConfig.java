package com.sparta.hanghaeblog.config;


<<<<<<< HEAD
import com.sparta.hanghaeblog.security.UserDetailsServiceImpl;
=======

import com.sparta.hanghaeblog.jwt.JwtAuthFilter;
import com.sparta.hanghaeblog.jwt.JwtUtil;
>>>>>>> 9f71c88e21feb1ac0f7fd7e037506a92b31749d8
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity // 스프링 Security 지원을 가능하게 함
public class WebSecurityConfig extends WebSecurityConfiguration {

<<<<<<< HEAD
    private final UserDetailsServiceImpl userDetailsService;
=======
    private final JwtUtil jwtUtil;
>>>>>>> 9f71c88e21feb1ac0f7fd7e037506a92b31749d8

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
<<<<<<< HEAD
=======
    }

    @Bean
    public WebSecurityCustomizer WebSecurityCustomizer(){
        return (web -> web.ignoring()
                .requestMatchers(PathRequest.toH2Console())
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()));
>>>>>>> 9f71c88e21feb1ac0f7fd7e037506a92b31749d8
    }

    @Bean
    public WebSecurityCustomizer WebSecurityCustomizer(){
        return (web -> web.ignoring()
                .requestMatchers(PathRequest.toH2Console())
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()));
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // CSRF 설정
        http.csrf().disable();

<<<<<<< HEAD
=======

>>>>>>> 9f71c88e21feb1ac0f7fd7e037506a92b31749d8
        http.authorizeRequests()
                .antMatchers("/api/auth/**")
                .permitAll()
                .anyRequest()
                .authenticated();
<<<<<<< HEAD
=======

>>>>>>> 9f71c88e21feb1ac0f7fd7e037506a92b31749d8

        // 로그인 사용
        http.formLogin();

<<<<<<< HEAD
        http.addFilterBefore(new CustomSecurityFilter(userDetailsService, passwordEncoder()), UsernamePasswordAuthenticationFilter.class);
=======

        http.addFilterBefore(new JwtAuthFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);

>>>>>>> 9f71c88e21feb1ac0f7fd7e037506a92b31749d8

        return http.build();
    }

}