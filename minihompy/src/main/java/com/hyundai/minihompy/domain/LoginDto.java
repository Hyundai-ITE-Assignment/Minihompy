package com.hyundai.minihompy.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*************************************************************
파일명: LoginDto.java
기능: 로그인 DTO
작성자: 유지훈

[코멘트: X]
*************************************************************/
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {

    private String username;
    private String password;
}
