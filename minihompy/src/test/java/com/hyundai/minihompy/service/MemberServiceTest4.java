package com.hyundai.minihompy.service;

import java.sql.SQLException;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Log4j2
@SpringBootTest
public class MemberServiceTest4 {

  @Autowired
  MemberService memberService;

  @Test
  public void deleteMemberTest() {
    try{
      String memberId = "dotori123";
      memberService.deleteMember(memberId);

      log.info(memberService.findById(memberId, 0));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
