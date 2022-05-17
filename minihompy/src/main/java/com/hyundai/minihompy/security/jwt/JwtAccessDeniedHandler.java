package com.hyundai.minihompy.security.jwt;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

/*************************************************************
 파일명: JwtAccessDeniedHandler.java
 기능: 권한없는 접근시 에러 발생
 작성자: 유지훈

 [코멘트: X]
 *************************************************************/
@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

  // 필요한 권한이 존재하지 않는 경우에 403 forbidden 에러를 리턴
  @Override
  public void handle(
    HttpServletRequest request,
    HttpServletResponse response,
    AccessDeniedException accessDeniedException
  ) throws IOException, ServletException {
      response.sendError(HttpServletResponse.SC_FORBIDDEN);
  }
}
