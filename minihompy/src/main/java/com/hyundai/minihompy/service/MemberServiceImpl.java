package com.hyundai.minihompy.service;

import com.hyundai.minihompy.dao.MemberDAO;
import com.hyundai.minihompy.domain.MemberDTO;
import com.hyundai.minihompy.domain.MemberRole;
import com.hyundai.minihompy.domain.MemberRoleSet;
import java.sql.SQLException;
import java.time.LocalDate;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Log4j2
public class MemberServiceImpl implements MemberService{

  @Autowired
  private MemberDAO memberDAO;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Transactional
  @Override
  public void insertMember(MemberDTO memberDTO) {

    try {
      if(memberDAO.findById(memberDTO.getId(), 0) != null){
        throw new RuntimeException("이미 가입되어 있는 회원아이디입니다.");
      }

      memberDTO.setPassword(passwordEncoder.encode(memberDTO.getPassword()));
      memberDTO.setRegdate(LocalDate.now());

      log.info("회원 정보 : "+memberDTO.getId());
      log.info("회원 정보 : "+memberDTO.getPassword());
      log.info("회원 정보 : "+memberDTO.getEmail());
      log.info("회원 정보 : "+memberDTO.getName());
      log.info("회원 정보 : "+memberDTO.getRegdate());
      memberDAO.insertMember(memberDTO);

      MemberRoleSet memberRoleSet = new MemberRoleSet();
      memberRoleSet.setMember_id(memberDTO.getId());
      memberRoleSet.setRole_set(String.valueOf(MemberRole.USER));
      memberDAO.insertMemberRoleSet(memberRoleSet);
    } catch (SQLException throwables) {
      log.info(throwables.getMessage());
    }
  }

  @Transactional(readOnly = true)
  @Override
  public MemberDTO findById(String id, int social) {
    MemberDTO findMember = null;
    try {
      findMember = memberDAO.findById(id, social);
    } catch (SQLException throwables) {
      log.info(throwables.getMessage());
    }
    return findMember;
  }

  @Transactional
  @Override
  public void updateMember(MemberDTO memberDTO) {
    try {
      memberDAO.updateMember(memberDTO);
    } catch (SQLException throwables) {
      log.info(throwables.getMessage());
    }
  }

  @Override
  public void deleteMember(String id) {
    try {
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
