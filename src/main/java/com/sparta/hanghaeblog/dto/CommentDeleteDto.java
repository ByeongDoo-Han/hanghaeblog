package com.sparta.hanghaeblog.dto;

import lombok.Getter;

@Getter
public class CommentDeleteDto{
        private String username;
        private boolean admin = false;
        private String commentPassword;
}
