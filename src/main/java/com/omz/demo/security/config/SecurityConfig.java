package com.omz.demo.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.omz.demo.client.repository.ClientRepository;
import com.omz.demo.security.jwt.JwtAuthenticationFilter;
import com.omz.demo.security.jwt.JwtAutorizationFilter;
import com.omz.demo.security.service.CorsConfig;

@Configuration
@EnableWebSecurity // SpringSecurityFilterChain에 등록
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig {
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Bean
	public BCryptPasswordEncoder encodePassword() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	private CorsConfig corsConfig;
	
	@Bean
	// 사용자 요청을 받아 위임하면서 찾게 되는게 SecurityFilterChain(FilterChainProxy)이며, 
	// 위임받은 요청을 각각의 Filter에게 순서대로 요청하는데 각각의 필터가 체인으로 연결되어 수행>넘김>수행>넘김으로 진행되며 이때 수행되는 메소드가 doFilter
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception { // 실제 필터를 생성하는 클래스 HttpSecurity

        http.csrf().disable();
		http.formLogin().disable();
		http.httpBasic().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.apply(new MyCustomerFilter());
		http.authorizeHttpRequests()
		.antMatchers("/", "/signup", "/signup/*", "/update", "/editinfo/*", "/movie/**", "/review/list/**", "/review/detail/**", "/review/page/**", "/comment/list/**",
				"/board/list/**", "/board/write", "/view/check/**", "/search/**",
				"/board/view/**", "/board/update/**", "/board/delete/**", "/board/contentdownload/**")
		.permitAll() // 로그인 없이 접근 허용
		.anyRequest().authenticated(); // 그외 모든 요청에 대해서 인증(로그인)이 필요
		
		return http.build();
	}
	
	public class MyCustomerFilter extends AbstractHttpConfigurer<MyCustomerFilter, HttpSecurity> {
		@Override
		public void configure(HttpSecurity http) throws Exception {
			AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
			http.addFilter(corsConfig.corsFilter()); // @CrossOrigin(인증 X), Security Filter에 등록 인증(O)
			http.addFilter(new JwtAuthenticationFilter(authenticationManager))// 인증 필터 등록
					.addFilter(new JwtAutorizationFilter(authenticationManager, clientRepository));
		}

	}

}
