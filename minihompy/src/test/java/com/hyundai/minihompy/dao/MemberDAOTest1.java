package com.hyundai.minihompy.dao;

import com.hyundai.minihompy.domain.MemberDTO;
import java.sql.SQLException;
import java.time.LocalDate;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Log4j2
@SpringBootTest
public class MemberDAOTest1 {

  @Autowired
  private MemberDAO memberDAO;

  @Test
  public void insertMemberTest(){
    try{
      MemberDTO memberDTO = new MemberDTO();
      memberDTO.setId("dotori123");
      memberDTO.setPassword("1234");
      memberDTO.setRegdate(LocalDate.now());
      memberDTO.setEmail("dotori@naver.com");

      log.info(memberDTO);
      memberDAO.insertMember(memberDTO);
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
  }
}
