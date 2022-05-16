package com.hyundai.minihompy.config;

import com.hyundai.minihompy.security.jwt.JwtAccessDeniedHandler;
import com.hyundai.minihompy.security.jwt.JwtAuthenticationEntryPoint;
import com.hyundai.minihompy.security.jwt.JwtSecurityConfig;
import com.hyundai.minihompy.security.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Log4j2
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) //@preAuthorize 어노테이션을 메소드 단위로 추가위해 사용
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final TokenProvider tokenProvider;
	private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

	public SecurityConfig(
		TokenProvider tokenProvider,
		JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
		JwtAccessDeniedHandler jwtAccessDeniedHandler
	){
		this.tokenProvider = tokenProvider;
		this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
		this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable() //crsf 비활성화

			.exceptionHandling()
			.authenticationEntryPoint(jwtAuthenticationEntryPoint)
			.accessDeniedHandler(jwtAccessDeniedHandler)

			.and()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS) //세션 사용 안함 설정

			.and()
			.authorizeRequests() //httpServletRequest를 사용하는 요청들에 대한 접근제한 설정
			.antMatchers("/members/authenticate").permitAll() //해당 url은 인증없이 항상 허용한다.
			.antMatchers("/members/signup").permitAll()
			.antMatchers("/api/authenticate").permitAll()
			.antMatchers("/*").permitAll()
//			.anyRequest().authenticated() //그 밖에 접근은 인증을 받아야 한다.
		
			.and()
			.apply(new JwtSecurityConfig(tokenProvider)); //Jwtfilter를 addFilterBefore로 등록했던 JwtSecurityConfig 클래스도 적용

	}
}
