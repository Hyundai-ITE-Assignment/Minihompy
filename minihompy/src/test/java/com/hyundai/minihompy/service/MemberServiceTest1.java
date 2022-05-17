package com.hyundai.minihompy.service;


import com.hyundai.minihompy.domain.BoardDTO;
import com.hyundai.minihompy.domain.MemberDTO;
import java.time.LocalDate;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Log4j2
@SpringBootTest
public class MemberServiceTest1 {

  @Autowired
  private MemberService memberService;

  @Test
  public void memberInsertTest() {
    try {
      MemberDTO memberDTO = new MemberDTO();
      memberDTO.setId("dotori123");
      memberDTO.setPassword("1234");
      memberDTO.setRegdate(LocalDate.now());
      memberDTO.setEmail("dotori@naver.com");

      log.info(memberDTO);
      memberService.insertMember(memberDTO);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
