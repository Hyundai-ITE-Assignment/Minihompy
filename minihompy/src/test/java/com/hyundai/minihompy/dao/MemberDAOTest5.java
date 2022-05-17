package com.hyundai.minihompy.dao;

import com.hyundai.minihompy.domain.MemberRoleSet;
import java.sql.SQLException;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Log4j2
@SpringBootTest
public class MemberDAOTest5 {

  @Autowired
  private MemberDAO memberDAO;

  @Test
  public void deleteMemberTest(){
    try{
      String memberId = "dotori123";
      memberDAO.deleteMemberRole(memberId);
      memberDAO.deleteMember(memberId);

      log.info(memberDAO.findById(memberId, 0));
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
  }
}
