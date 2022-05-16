package com.hyundai.minihompy.dao;

import com.hyundai.minihompy.domain.MemberDTO;
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
  public void insertTest(){
    MemberDTO memberDTO = new MemberDTO();
    memberDTO.setId("dbwlgns98");
  }

}
