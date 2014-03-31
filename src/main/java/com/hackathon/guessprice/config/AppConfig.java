package com.hackathon.guessprice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.hackathon.guessprice.service.UserService;

@Configuration
@ComponentScan("com.hackathon.guessprice")
public class AppConfig {

	@Bean
	public UserService userService(){
		return new UserService();
	}
}
