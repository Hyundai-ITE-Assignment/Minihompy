package com.hyundai.minihompy.domain;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*************************************************************
파일명: MemberRoleSet.java
기능: 회원 Role Set
작성자: 유지훈

[코멘트: X]
*************************************************************/
@Getter
@Setter
@ToString
public class MemberRoleSet implements Serializable {
    private String member_id;
    private String role_set;
}
