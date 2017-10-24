package com.cong.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.WebApplicationInitializer;

import com.cong.config.SecurityConfiguration;
import com.cong.controller.UserController;

@SpringBootApplication
public class CognitevApplication extends SpringBootServletInitializer implements WebApplicationInitializer {

	public static void main(String[] args) {
		SpringApplication.run(CognitevApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(CognitevApplication.class);
	}

	@Bean
	public UserController UserController() {
		return new UserController();
	}

	@Bean
	public SecurityConfiguration securityConfiguration() {
		return new SecurityConfiguration();
	}
}