package com.hyundai.minihompy.service;

import java.sql.SQLException;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hyundai.minihompy.dao.MemberDAO;
import com.hyundai.minihompy.domain.MemberDTO;
import com.hyundai.minihompy.domain.MemberRole;
import com.hyundai.minihompy.domain.MemberRoleSet;

import lombok.extern.log4j.Log4j2;

/*************************************************************
파일명: MemberServiceImpl.java
기능: 회원 관련 Service
작성자: 유지훈

[코멘트: X]
*************************************************************/
@Service
@Log4j2
public class MemberServiceImpl implements MemberService{

  //회원 DAO 주입
  @Autowired
  private MemberDAO memberDAO;

  //암호화 객체 주입
  @Autowired
  private PasswordEncoder passwordEncoder;

  //회원 가입 기능
  @Transactional
  @Override
  public void insertMember(MemberDTO memberDTO) {

    //중복된 아이디의 경우 롤백처리를 해준다.
    try {
      if(memberDAO.findById(memberDTO.getId(), 0) != null){
        throw new RuntimeException("이미 가입되어 있는 회원아이디입니다.");
      }

      // 회원 비밀번호 설정 및 현재 생성날짜 추가
      memberDTO.setPassword(passwordEncoder.encode(memberDTO.getPassword()));
      memberDTO.setRegdate(LocalDate.now());

      log.info("회원 정보 : "+memberDTO.getId());
      log.info("회원 정보 : "+memberDTO.getPassword());
      log.info("회원 정보 : "+memberDTO.getEmail());
      log.info("회원 정보 : "+memberDTO.getName());
      log.info("회원 정보 : "+memberDTO.getRegdate());
      //회원 insert 기능
      memberDAO.insertMember(memberDTO);

      //회원 권한 설정 및 insert 기능
      MemberRoleSet memberRoleSet = new MemberRoleSet();
      memberRoleSet.setMember_id(memberDTO.getId());
      memberRoleSet.setRole_set(String.valueOf(MemberRole.USER));
      memberDAO.insertMemberRoleSet(memberRoleSet);
    } catch (SQLException throwables) {
      log.info(throwables.getMessage());
    }
  }

  // 회원 조회 기능
  @Transactional(readOnly = true)
  @Override
  public MemberDTO findById(String id, int social) {
    MemberDTO findMember = null;
    try {
      //회원 조회 로직 수행
      findMember = memberDAO.findById(id, social);
    } catch (SQLException throwables) {
      log.info(throwables.getMessage());
    }
    return findMember;
  }

  // 회원 업데이트 기능
  @Transactional
  @Override
  public void updateMember(MemberDTO memberDTO) {
    try {
      memberDAO.updateMember(memberDTO);
    } catch (SQLException throwables) {
      log.info(throwables.getMessage());
    }
  }

  // 회원 삭제 기능
  @Override
  public void deleteMember(String id) {
    try {
      //회원 권한 삭제 및 회원 삭제 로직 수행
      memberDAO.deleteMemberRole(id);
      memberDAO.deleteMember(id);
    } catch (SQLException throwables) {
      log.info(throwables.getMessage());
    }
  }

	@Override
	public void addDotori(String id) {
		try {
			memberDAO.addDotori(id);
		} catch (SQLException throwables) {
			log.info(throwables.getMessage());
		}
		
	}
}
