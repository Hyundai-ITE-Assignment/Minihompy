package com.hyundai.minihompy.dao;

import com.hyundai.minihompy.domain.MemberDTO;
import com.hyundai.minihompy.domain.MemberRoleSet;
import java.sql.SQLException;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/*************************************************************
파일명: MemberDAO.java
기능: 회원 관련 DAO
작성자: 유지훈

[코멘트: X]
*************************************************************/
@Mapper
public interface MemberDAO {

     //회원 insert
     void insertMember(MemberDTO memberDTO) throws SQLException;

     //회원 권한 생성 insert
     void insertMemberRoleSet(MemberRoleSet memberRoleSet) throws SQLException;

     //회원 조회
     MemberDTO findById(@Param("id") String id, @Param("social") int social) throws SQLException;

     //회원 update 기능
     void updateMember(MemberDTO memberDTO) throws SQLException;

     //회원 delete 기능
     void deleteMember(@Param("id") String id) throws SQLException;

     //회원 권한 delete 기능
     void deleteMemberRole(@Param("member_id") String member_id) throws SQLException;

     //회원 도토리 갯수 증가
     void addDotori(@Param("id") String id) throws SQLException;
}
