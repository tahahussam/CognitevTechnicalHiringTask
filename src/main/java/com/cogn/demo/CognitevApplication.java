package com.cogn.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.WebApplicationInitializer;

import com.cogn.config.SecurityConfiguration;
import com.cogn.controller.UserController;
import com.cogn.service.UserService;

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
	public UserService UserService() {
		return new UserService();
	}

	@Bean
	public SecurityConfiguration securityConfiguration() {
		return new SecurityConfiguration();
	}
}