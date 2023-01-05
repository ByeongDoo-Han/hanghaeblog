package com.sparta.hanghaeblog.dto;

import lombok.Getter;

@Getter
public class ErrorCodeDto {
    private String errorMessage;
    private int statusCode;
}
