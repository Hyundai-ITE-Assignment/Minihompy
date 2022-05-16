package com.hyundai.minihompy.controller;

import com.hyundai.minihompy.security.jwt.TokenProvider;
import com.hyundai.minihompy.service.MemberService;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
public class MemberController {

  private final MemberService memberService;
  private final TokenProvider tokenProvider;
  private final AuthenticationManagerBuilder authenticationManagerBuilder;

  public MemberController(
    TokenProvider tokenProvider,
    AuthenticationManagerBuilder authenticationManagerBuilder,
    MemberService memberService
  ) {
    this.tokenProvider = tokenProvider;
    this.authenticationManagerBuilder = authenticationManagerBuilder;
    this.memberService = memberService;
  }

}
