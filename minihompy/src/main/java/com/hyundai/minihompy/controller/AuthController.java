package com.hyundai.minihompy.controller;

import com.hyundai.minihompy.domain.LoginDto;
import com.hyundai.minihompy.domain.TokenDto;
import com.hyundai.minihompy.security.jwt.JwtFilter;
import com.hyundai.minihompy.security.jwt.TokenProvider;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*************************************************************
 파일명: AuthController.java
 기능: 토큰 발급 Controller
 작성자: 유지훈

 [코멘트: 회원별 토큰을 발급받을 수 있다.]
 *************************************************************/
@Slf4j
@RestController
@RequestMapping("/api")
public class AuthController {
  private final TokenProvider tokenProvider;
  private final AuthenticationManagerBuilder authenticationManagerBuilder;

  public AuthController(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder) {
    this.tokenProvider = tokenProvider;
    this.authenticationManagerBuilder = authenticationManagerBuilder;
  }

  //회원 로그인할때 토큰 생성을 해준다.
  @PostMapping("/authenticate")
  public Map<String, String> authorize(@RequestBody LoginDto loginDto) {

    UsernamePasswordAuthenticationToken authenticationToken =
      new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());

    //권한 조회
    Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
    SecurityContextHolder.getContext().setAuthentication(authentication);

    //토큰 생성
    String jwt = tokenProvider.createToken(authentication);

    //토큰 담기
    Map<String, String> map = new HashMap<>();
    map.put("result", "success");
    map.put("id", loginDto.getUsername());
    map.put("jwt", jwt);
    return map;
  }
}
