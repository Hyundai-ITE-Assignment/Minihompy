package com.hyundai.minihompy.dao;

import com.hyundai.minihompy.domain.MemberDTO;
import com.hyundai.minihompy.domain.MemberRoleSet;
import java.sql.SQLException;
import java.time.LocalDate;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Log4j2
@SpringBootTest
public class MemberDAOTest3 {

  @Autowired
  private MemberDAO memberDAO;

  @Test
  public void insertMemberRoleSetTest(){
    try{
      MemberRoleSet memberRoleSet = new MemberRoleSet();
      memberRoleSet.setMember_id("dotori123");
      memberRoleSet.setRole_set("ADMIN");

      memberDAO.insertMemberRoleSet(memberRoleSet);
      log.info(memberRoleSet);
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
  }
}
