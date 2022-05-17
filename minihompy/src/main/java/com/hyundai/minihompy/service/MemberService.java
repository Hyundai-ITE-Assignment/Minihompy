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

    // 회원 정보 등록
    void insertMember(MemberDTO memberDTO) throws SQLException;

    // 회원 조회 기능
    MemberDTO findById(String id, int social);

    // 회원 update 기능
    void updateMember(MemberDTO memberDTO);

    //회원 삭제 기능
    void deleteMember(String id);

    //회원의 도토리 갯수 증가 기능
    void addDotori(String id);
}
