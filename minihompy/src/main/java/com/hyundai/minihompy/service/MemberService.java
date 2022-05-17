package com.hyundai.minihompy.service;

import java.sql.SQLException;

import com.hyundai.minihompy.domain.MemberDTO;

/*************************************************************
파일명: MemberService.java
기능: 회원 관련 Service 인터페이스
작성자: 유지훈

[코멘트: X]
*************************************************************/
public interface MemberService {

    void insertMember(MemberDTO memberDTO) throws SQLException;

    MemberDTO findById(String id, int social);

    void updateMember(MemberDTO memberDTO);

    void deleteMember(String id);
    
    void addDotori(String id);
}
