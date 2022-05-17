package com.hyundai.minihompy.dao;

import com.hyundai.minihompy.domain.MemberDTO;
import com.hyundai.minihompy.domain.MemberRoleSet;
import java.sql.SQLException;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Log4j2
@SpringBootTest
public class MemberDAOTest4 {

  @Autowired
  private MemberDAO memberDAO;

  @Test
  public void findByIdTest(){
    try{
      MemberDTO findMember = memberDAO.findById("dotori123", 0);
      log.info(findMember);
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
  }
}
