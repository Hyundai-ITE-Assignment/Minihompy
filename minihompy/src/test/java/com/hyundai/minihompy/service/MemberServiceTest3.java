package com.hyundai.minihompy.service;

import com.hyundai.minihompy.domain.MemberDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Log4j2
@SpringBootTest
public class MemberServiceTest3 {

  @Autowired
  MemberService memberService;

  @Test
  public void findMemberTest(){
    try{
      MemberDTO findMember = memberService.findById("dotori123", 0);
      log.info(findMember);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
