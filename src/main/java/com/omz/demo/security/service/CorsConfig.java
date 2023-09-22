package com.omz.demo.security.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
public class CorsConfig {
	
	@Bean
	public CorsFilter corsFilter() {
		System.out.println("corsFilter============================");
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true); // 내 서버가 응답할 때 json을 javascript에서 처리할 수 있도록 설정 
		corsConfiguration.addAllowedOriginPattern("*"); // 포트번호 응답 다름 허용
		corsConfiguration.addAllowedHeader("*"); // 모든 요청 header에 응답 허용
		corsConfiguration.addAllowedMethod("*"); // 모든 post, get, put, delete, patch 요청에 응답 허용
		
//		corsConfiguration.addExposedHeader();
		source.registerCorsConfiguration("/**", corsConfiguration);
	
		return new CorsFilter(source);
	}

}
