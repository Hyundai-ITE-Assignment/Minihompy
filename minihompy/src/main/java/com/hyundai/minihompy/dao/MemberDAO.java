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

     void insertMember(MemberDTO memberDTO) throws SQLException;

     void insertMemberRoleSet(MemberRoleSet memberRoleSet) throws SQLException;

     MemberDTO findById(@Param("id") String id, @Param("social") int social) throws SQLException;

     void updateMember(MemberDTO memberDTO) throws SQLException;

     void deleteMember(@Param("id") String id) throws SQLException;

     void deleteMemberRole(@Param("member_id") String member_id) throws SQLException;
     
     void addDotori(@Param("id") String id) throws SQLException;
}
