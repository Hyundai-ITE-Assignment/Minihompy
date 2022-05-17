package com.hyundai.minihompy.service;

import com.hyundai.minihompy.domain.MemberDTO;
import java.time.LocalDate;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Log4j2
@SpringBootTest
public class MemberServiceTest2 {

  @Autowired
  private MemberService memberService;

  @Test
  public void updateMemberTest(){
    try{
      MemberDTO updateMember = new MemberDTO();
      updateMember.setName("뉴지훈");
      updateMember.setEmail("update@naver.com");
      updateMember.setModdate(LocalDate.now());

      memberService.updateMember(updateMember);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
