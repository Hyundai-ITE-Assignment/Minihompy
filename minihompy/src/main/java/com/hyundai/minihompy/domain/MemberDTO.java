package com.hyundai.minihompy.domain;

import java.time.LocalDate;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*************************************************************
파일명: MemberDTO.java
기능: 회원 DTO
작성자: 유지훈

[코멘트: X]
*************************************************************/
@Getter
@Setter
@ToString
public class MemberDTO {

    private String id;
    private String name;
    private String password;
    private String email;
    private int dotori;
    private int from_social;
    private LocalDate moddate;
    private LocalDate regdate;

    private List<MemberRoleSet> authorities;
}
