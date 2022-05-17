package com.hyundai.minihompy.security.jwt;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/*************************************************************
 파일명: JwtAuthenticationEntryPoint.java
 기능: 유효한 권한을 제공하지 않고 접근하려 할 때 401에러 발생
 작성자: 유지훈

 [코멘트: X]
 *************************************************************/
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

  //유효한 자격증명을 제공하지 않고 접근하려 할때 401 Unauthorized 에러를 리턴
  @Override
  public void commence(
    HttpServletRequest request,
    HttpServletResponse response,
    AuthenticationException authException
  ) throws IOException, ServletException {
    response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
  }
}
